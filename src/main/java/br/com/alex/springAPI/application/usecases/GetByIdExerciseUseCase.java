package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.domain.interfaces.IRepositoryDomain;
import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.exception.NotFoundExpection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GetByIdExerciseUseCase {

  private final IRepositoryDomain<Exercise, ExerciseId> exerciseRepository;


  public ExerciseOutput execute(ExerciseId id) {

    var exerciseInDb = this.exerciseRepository
        .findById(id).orElseThrow(() -> new NotFoundExpection("Não há exercisios com o id fornecido", Optional.of("Forneça outro Id")));


    return ExerciseOutput.of(exerciseInDb);
  }
}
