package br.com.alex.springAPI.application.output;

import lombok.Builder;
import java.util.Set;

@Builder
public record WorkoutDetailOutput(
        String workoutName,
        String objective,
        String studentName,
        Set<ExerciseOutput> exercises ) {

}
