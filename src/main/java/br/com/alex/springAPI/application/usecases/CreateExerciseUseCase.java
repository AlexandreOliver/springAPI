package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import br.com.alex.springAPI.application.input.CreateExerciseInput;
import br.com.alex.springAPI.application.output.ExerciseOutput;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateExerciseUseCase {

  private final IExerciseRepository exerciseRepository;


  public ExerciseOutput execute(CreateExerciseInput exerciseInput) {

    var exercise = Exercise.builder()
        .id(new ExerciseId())
        .name(exerciseInput.name())
        .grupoMuscular(exerciseInput.grupoMuscular())
        .equipament(exerciseInput.equipament())
        .difficultLevel(exerciseInput.difficultLevel())
        .build();

    var exerciseSalved = exerciseRepository.save(exercise);

    return ExerciseOutput.of(exerciseSalved);
  }
}
