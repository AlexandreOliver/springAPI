package br.com.alex.springAPI.application.output;

import br.com.alex.springAPI.domain.Exercise;

import java.util.UUID;

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
