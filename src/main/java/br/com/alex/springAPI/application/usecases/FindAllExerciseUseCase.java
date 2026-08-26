package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.domain.interfaces.IRepositoryDomain;
import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FindAllExerciseUseCase {

  private final IRepositoryDomain<Exercise, ExerciseId> exerciseRepository;

  public List<ExerciseOutput> execute() {

    return exerciseRepository.findAll().stream().map(ExerciseOutput::of).toList();
  }
}
