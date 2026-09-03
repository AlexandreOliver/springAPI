package br.com.alex.springAPI.application.usecases;


import br.com.alex.springAPI.application.input.FindAllCommand;
import br.com.alex.springAPI.application.output.WorkoutOutput;
import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.domain.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindAllWorkoutUseCase {

  private final IWorkoutRepository workoutRepository;

  public Pagination<WorkoutOutput> execute(FindAllCommand input) {

    Pagination<Workout> pageInDb = this.workoutRepository.findAll(input.request_page(), input.query());

    return pageInDb.map(WorkoutOutput::of);
  }

}
