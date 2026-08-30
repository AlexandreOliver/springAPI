package br.com.alex.springAPI.infrastructure.persistence.jpa.entity;

import br.com.alex.springAPI.domain.PhisicalAssessment;
import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;
import br.com.alex.springAPI.domain.valueObjects.Price;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "phisical_assessments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhisicalAssessmentEntity {

  @Id
  private UUID id;

  @Column(nullable = false)
  private Integer preco;

  @Column(nullable = false)
  private BigDecimal altura;

  @Column(nullable = false, name = "percent_body_fat")
  private BigDecimal percentBodyFat;

  public static PhisicalAssessmentEntity from(PhisicalAssessment assessment) {
    return PhisicalAssessmentEntity.builder()
        .id(assessment.getId().uuid())
        .preco(assessment.getPreco().inCents())
        .altura(new BigDecimal(assessment.getAltura()))
        .percentBodyFat(new BigDecimal(assessment.getPercentBodyFat()))
        .build();
  }

  public PhisicalAssessment toDomain() {
    return PhisicalAssessment.builder()
        .id(new PhisicalAssessmentId(this.id))
        .altura(this.altura.doubleValue())
        .preco(Price.ofCents(this.preco.longValue()))
        .percentBodyFat(this.percentBodyFat.doubleValue())
        .build();
  }


}
