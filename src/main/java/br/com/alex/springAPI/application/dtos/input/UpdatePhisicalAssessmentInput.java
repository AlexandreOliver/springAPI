package br.com.alex.springAPI.application.dtos.input;

import java.util.Optional;

public record UpdatePhisicalAssessmentInput(
    Optional<String> name,

    Optional<String> grupoMuscular,

    Optional<String> equipament,

    Optional<Integer> difficultLevel
) { }
