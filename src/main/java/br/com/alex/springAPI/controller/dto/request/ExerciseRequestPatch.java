package br.com.alex.springAPI.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ExerciseRequestPatch(
    @Size(min = 2, max = 20) String nome,
    String grupoMuscular,
    String equipament,
    @Min(1) @Max(3) Integer difficultLevel
) { }
