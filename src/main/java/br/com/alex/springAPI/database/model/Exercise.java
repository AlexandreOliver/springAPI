package br.com.alex.springAPI.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "exercices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, name = "grupo_muscular")
  private String grupoMuscular;

  @Column(name = "equipament", nullable = false)
  private String equipament;

  @Column(name="difficult_level", nullable = false)
  private Integer difficultLevel;

}