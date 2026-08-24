package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.input.UpdateExerciseInput;
import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.exception.NotFoundExpection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateExerciseUseCase {

  private final IExerciseRepository exerciseRepository;


  public ExerciseOutput execute(UpdateExerciseInput exerciseInput, ExerciseId id) {

    var exerciseInDb = exerciseRepository.findById(id)
        .orElseThrow(() -> new NotFoundExpection(
        "Não há exercicios para o Id fornecido", Optional.of("Forneça outro Id")));

    exerciseInDb.update(exerciseInput.name(), exerciseInput.grupoMuscular(), exerciseInput.equipament(), exerciseInput.difficultLevel());

    var exerciseSalved = exerciseRepository.save(exerciseInDb);

    return ExerciseOutput.from(exerciseSalved);
  }

}
