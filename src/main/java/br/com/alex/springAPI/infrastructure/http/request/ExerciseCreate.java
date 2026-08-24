package br.com.alex.springAPI.infrastructure.http.request;

import br.com.alex.springAPI.application.input.CreateExerciseInput;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record ExerciseCreate(
    @NotBlank
    @Size(min = 2, max = 20)
    String name,

    @NotBlank
    String grupoMuscular,

    @NotBlank
    String equipament,

    @NotNull
    @Min(1)
    @Max(3)
    Integer difficultLevel
) {
  public CreateExerciseInput toInput() {
    return new CreateExerciseInput(name, grupoMuscular, equipament, difficultLevel);
  }
}

