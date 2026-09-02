package br.com.alex.springAPI.application.output;

import br.com.alex.springAPI.domain.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(description = "Saida de um estudante com sua avaliação física")
public record StudentOutput(
    UUID Id,
    String name,
    String email,
    AssessmentOutput assessment
) {
  public static StudentOutput of(Student student) {
    var building = StudentOutput.builder()
        .Id(student.getId().uuid())
        .name(student.getName())
        .email(student.getEmail());

    if (student.getPhysicalAssessment() != null) {
      building.assessment(AssessmentOutput.of(student.getPhysicalAssessment()));
    }

    return building.build();
  }
}

