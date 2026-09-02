package br.com.alex.springAPI.domain.valueObjects;

import lombok.Builder;

@Builder
public record WorkoutDetail(
        WorkoutId workoutId,
        String workoutName,
        String objective,
        String studentName
    )
{ }
