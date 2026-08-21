package br.com.alex.springAPI.controller.dto.response;

import br.com.alex.springAPI.database.model.Student;
import lombok.Builder;

import java.util.Set;

@Builder
public record WorkoutResponse(
    Long id,
    String name,
    String objective,
    Student student,
    Set<ExerciseResponse> exercises
) {
}


