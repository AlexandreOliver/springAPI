package br.com.alex.springAPI.application.dtos.output;

import br.com.alex.springAPI.domain.Exercise;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Saida de um exercício")
public record ExerciseOutput(
    UUID id,
    String name,
    String grupoMuscular,
    String equipament,
    int difficultLevel
) {

  public static ExerciseOutput of(Exercise exercise) {
    return new ExerciseOutput(
        exercise.getId().id(),
        exercise.getName(),
        exercise.getGrupoMuscular(),
        exercise.getEquipament(),
        exercise.getDifficultLevel());
  }
}
