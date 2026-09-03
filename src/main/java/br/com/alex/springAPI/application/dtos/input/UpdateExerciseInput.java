package br.com.alex.springAPI.application.dtos.input;

import java.util.Optional;

public record UpdateExerciseInput(
    Optional<String> name,

    Optional<String> grupoMuscular,

    Optional<String> equipament,

    Optional<Integer> difficultLevel
) {
}
