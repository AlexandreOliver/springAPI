package br.com.alex.springAPI.controller.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record ExerciseRequestCreate(
    @NotBlank @Size(min = 2, max = 20) String nome,
    @NotBlank String grupoMuscular,
    @NotBlank String equipament,
    @NotNull @Min(1) @Max(3) Integer difficultLevel
) { }

