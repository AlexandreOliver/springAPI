package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.application.interfaces.IExerciseRepository;

import br.com.alex.springAPI.application.dtos.output.ExerciseOutput;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindAllExerciseUseCase {

  private final IExerciseRepository exerciseRepository;

  public Pagination<ExerciseOutput> execute(PageApplication page) {

    return exerciseRepository.findAll(page).map(ExerciseOutput::of);
  }
}
