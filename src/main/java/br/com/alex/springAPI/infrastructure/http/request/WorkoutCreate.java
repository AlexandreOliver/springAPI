package br.com.alex.springAPI.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record WorkoutRequestCreate(
    @NotBlank String name,
    @NotBlank String objective,
    @NotNull Long studentId,
    @NotNull Set<UUID> exercises
) {
}
