package br.com.alex.springAPI.application.dtos;

import br.com.alex.springAPI.domain.valueObjects.WorkoutId;
import lombok.Builder;

@Builder
public record WorkoutDetail(
        WorkoutId workoutId,
        String workoutName,
        String objective,
        String studentName
    )
{ }
