package br.com.alex.springAPI.controller.dto.response;

import br.com.alex.springAPI.infrastructure.persistence.entity.ExerciseEntity;
import lombok.Builder;


import java.util.Set;

@Builder
public record WorkoutResponseWithoutStudent(
    Long id,
    String name,
    String objective,
    Set<ExerciseEntity> exercises
) {}
