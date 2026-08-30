package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.domain.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.input.CreateWorkoutInput;
import br.com.alex.springAPI.application.output.WorkoutOutput;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateWorkoutUseCase {

  private final IStudentRepository studentRepository;
  private final IWorkoutRepository workoutRepository;
  private final IExerciseRepository exerciseRepository;

  public WorkoutOutput execute(CreateWorkoutInput workoutInput) throws NotFoundError {

    this.studentRepository.findById(workoutInput.studentId()).orElseThrow(() -> new NotFoundError("Não Existe um aluno com esse Id"));

    Workout newWorkout = Workout.builder()
        .id(new WorkoutId())
        .name(workoutInput.name())
        .objective(workoutInput.objective())
        .studentId(workoutInput.studentId())
        .build();

    for (UUID id: workoutInput.exercises()) {
      Exercise exercise = this.exerciseRepository
          .findById(new ExerciseId(id))
          .orElseThrow(() -> new NotFoundError("Não existe um exercicio com o id: " + id));

      newWorkout.addExercise(exercise);
    }

    Workout workout = this.workoutRepository.save(newWorkout);

    return WorkoutOutput.of(workout);
  }

}
