package br.com.alex.springAPI.application.dtos.output;

import br.com.alex.springAPI.domain.Workout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Schema(description = "Saida de um treino com seus exercícios")
public record WorkoutOutput(
    UUID id,
    String name,
    String objective,
    Set<ExerciseOutput> exercises
) {

  public static WorkoutOutput of(Workout workout) {
    var building = WorkoutOutput
        .builder()
        .id(workout.getId().id())
        .name(workout.getName())
        .objective(workout.getObjective());

    if (Objects.nonNull(workout.getExercises())) {
      building.exercises(workout.getExercises().stream().map(ExerciseOutput::of).collect(Collectors.toSet()));
    }

    return building.build();

  }

}


