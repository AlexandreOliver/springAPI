package br.com.alex.springAPI.infrastructure.http.request;

import br.com.alex.springAPI.application.dtos.input.UpdateExerciseInput;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Optional;

@Builder
public record ExercisePatch(

    Optional<@Size(min = 2, max = 20) String> nome,

    Optional<String> grupoMuscular,

    Optional<String> equipament,

    Optional<@Min(1) @Max(3) Integer> difficultLevel
) {

  public UpdateExerciseInput toInput() {
    return new UpdateExerciseInput(nome, grupoMuscular, equipament, difficultLevel);
  }
}
