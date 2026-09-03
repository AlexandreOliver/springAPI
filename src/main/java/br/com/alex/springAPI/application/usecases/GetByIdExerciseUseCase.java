package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.dtos.output.ExerciseOutput;

import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class GetByIdExerciseUseCase {

  private final IExerciseRepository exerciseRepository;

  public ExerciseOutput execute(ExerciseId id) {

    var exerciseInDb = this.exerciseRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundError("Não há exercisios com o id fornecido"));

    return ExerciseOutput.of(exerciseInDb);
  }
}
