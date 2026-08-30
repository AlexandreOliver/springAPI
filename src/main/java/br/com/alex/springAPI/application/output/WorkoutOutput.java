package br.com.alex.springAPI.controller.dto.response;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record WorkoutResponse(
    Long id,
    String name,
    String objective,
    StudentEntity student,
    Set<ExerciseResponse> exercises
) {
}


