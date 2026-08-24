package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FindAllExerciseUseCase {

  private final IExerciseRepository exerciseRepository;

  public List<ExerciseOutput> execute() {

    return exerciseRepository.findAll().stream().map(ExerciseOutput::from).toList();
  }
}
