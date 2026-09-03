package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.dtos.input.FindWithQueryCommand;
import br.com.alex.springAPI.application.dtos.output.WorkoutOutput;
import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.application.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.application.dtos.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindAllWorkoutUseCase {

  private final IWorkoutRepository workoutRepository;

  public Pagination<WorkoutOutput> execute(FindWithQueryCommand input) {

    Pagination<Workout> pageInDb = this.workoutRepository.findAllWithQuery(input.request_page(), input.query());

    return pageInDb.map(WorkoutOutput::of);
  }

}
