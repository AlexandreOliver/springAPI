package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import br.com.alex.springAPI.application.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.output.WorkoutOutput;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetWorkoutByStudentIdUseCase {
  private final IWorkoutRepository workoutRepository;
  private final IStudentRepository studentRepository;

  public List<WorkoutOutput> execute(StudentId id, boolean includeExercise) throws NotFoundError {

    if (!this.studentRepository.existsByStudentId(id)) {
      throw new NotFoundError("Não existe aluno com esse Id");
    }

    return this.workoutRepository.findByStudentId(id, includeExercise).stream().map(WorkoutOutput::of).toList();
  }
}
