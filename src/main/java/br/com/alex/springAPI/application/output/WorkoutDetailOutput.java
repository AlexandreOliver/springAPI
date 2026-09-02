package br.com.alex.springAPI.application.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.Set;

@Builder
@Schema(description = "Saida de um treino com o nome do aluno e seus exercícios")
public record WorkoutDetailOutput(
        String workoutName,
        String objective,
        String studentName,
        Set<ExerciseOutput> exercises ) {

}
