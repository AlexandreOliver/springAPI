package br.com.alex.springAPI.controller.dto.response;

import lombok.Builder;

@Builder
public record ExerciseResponse(
    Long Id,
    String nome,
    String grupoMuscular,
    String equipament,
    Integer difficultLevel
) { }

