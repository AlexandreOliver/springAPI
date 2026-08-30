package br.com.alex.springAPI.infrastructure.http.request;

import br.com.alex.springAPI.application.input.CreateWorkoutInput;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record WorkoutCreate(
    @NotBlank String name,
    @NotBlank String objective,
    @NotNull Set<UUID> exercises
) {

  public CreateWorkoutInput toInput(UUID studentId) {
    return CreateWorkoutInput.builder()
        .name(this.name)
        .objective(this.objective)
        .exercises(this.exercises)
        .studentId(new StudentId(studentId))
        .build();
  }
}
