package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.input.UpdateExerciseInput;
import br.com.alex.springAPI.application.output.ExerciseOutput;

import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UpdateExerciseUseCase {

  private final IExerciseRepository exerciseRepository;

  public ExerciseOutput execute(UpdateExerciseInput exerciseInput, ExerciseId id) {

    var exerciseInDb = exerciseRepository.findById(id)
        .orElseThrow(() -> new NotFoundError(
        "Não há exercicios para o Id fornecido"));

    exerciseInDb.update(exerciseInput.name(), exerciseInput.grupoMuscular(), exerciseInput.equipament(), exerciseInput.difficultLevel());

    var exerciseSalved = exerciseRepository.save(exerciseInDb);

    return ExerciseOutput.of(exerciseSalved);
  }

}
