package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteExerciseUseCase {

  private final IExerciseRepository exerciseRepository;

  public void execute(ExerciseId id) {

    this.exerciseRepository.delete(id);
  }
}
