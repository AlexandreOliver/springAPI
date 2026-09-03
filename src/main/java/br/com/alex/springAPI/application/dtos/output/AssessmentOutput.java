package br.com.alex.springAPI.application.dtos.output;

import br.com.alex.springAPI.domain.PhysicalAssessment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(description = "Saida de uma avaliação física")
public record AssessmentOutput(
    UUID id,
    String preco,
    double altura,
    double percentBodyFat
) {

  public static AssessmentOutput of(PhysicalAssessment assessment) {
    return AssessmentOutput.builder()
        .id(assessment.getId().uuid())
        .preco(assessment.getPreco().amount().toString())
        .altura(assessment.getAltura())
        .percentBodyFat(assessment.getPercentBodyFat())
        .build();
  }
}
