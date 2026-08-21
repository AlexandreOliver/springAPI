package br.com.alex.springAPI.controller.dto.response;

import br.com.alex.springAPI.database.model.Exercise;
import lombok.Builder;


import java.util.Set;

@Builder
public record WorkoutResponseWithoutStudent(
    Long id,
    String name,
    String objective,
    Set<Exercise> exercises
) {}
