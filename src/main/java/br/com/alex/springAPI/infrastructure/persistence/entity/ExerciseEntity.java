package br.com.alex.springAPI.infrastructure.persistence.entity;

import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "exercices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExerciseEntity {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, name = "grupo_muscular")
  private String grupoMuscular;

  @Column(name = "equipament", nullable = false)
  private String equipament;

  @Column(name="difficult_level", nullable = false)
  private Integer difficultLevel;

  public static ExerciseEntity from(Exercise exercise) {
    return ExerciseEntity
        .builder()
        .id(exercise.getId().id())
        .name(exercise.getName())
        .grupoMuscular(exercise.getGrupoMuscular())
        .equipament(exercise.getEquipament())
        .difficultLevel(exercise.getDifficultLevel())
        .build();
  }

  public Exercise toDomain() {
    return Exercise
        .builder()
        .id(new ExerciseId(this.id))
        .name(this.name)
        .grupoMuscular(this.grupoMuscular)
        .equipament(this.equipament)
        .difficultLevel(this.difficultLevel)
        .build();
  }

}