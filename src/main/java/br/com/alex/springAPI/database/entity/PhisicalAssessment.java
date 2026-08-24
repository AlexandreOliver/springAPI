package br.com.alex.springAPI.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "phisical_assessments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhisicalAssessment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private BigDecimal preco;

  @Column(nullable = false)
  private BigDecimal altura;

  @Column(nullable = false, name = "percent_body_fat")
  private BigDecimal percentBodyFat;

}
