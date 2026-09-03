package br.com.alex.springAPI.application.usecases;


import br.com.alex.springAPI.application.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteWorkoutUseCase {
  private final IWorkoutRepository workoutRepository;

  public void execute(WorkoutId id) {
    this.workoutRepository.delete(id);
  }

}
