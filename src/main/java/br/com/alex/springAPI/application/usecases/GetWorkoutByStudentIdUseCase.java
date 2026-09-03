package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import br.com.alex.springAPI.application.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.dtos.output.WorkoutOutput;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetWorkoutByStudentIdUseCase {
  private final IWorkoutRepository workoutRepository;
  private final IStudentRepository studentRepository;

  public Pagination<WorkoutOutput> execute(StudentId id, boolean includeExercise, PageApplication page) throws NotFoundError {

    if (!this.studentRepository.existsByStudentId(id)) {
      throw new NotFoundError("Não existe aluno com esse Id");
    }

    return this.workoutRepository.findByStudentId(id, includeExercise, page).map(WorkoutOutput::of);
  }
}
