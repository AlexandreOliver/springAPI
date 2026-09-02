package br.com.alex.springAPI.domain;

import br.com.alex.springAPI.domain.exception.DuplicatedError;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Workout {
  private WorkoutId id;

  private String name;

  private String objective;

  private Set<Exercise> exercises;

  private StudentId studentId;

  public void update(
      Optional<WorkoutId> id,
      Optional<String> name,
      Optional<String> objective
  ) {
    id.ifPresent(value -> this.id = value);
    name.ifPresent(value -> this.name = value);
    objective.ifPresent(value -> this.objective = value);
  }

  public void addExercise(Exercise exercise) {
    if (!(exercises.add(exercise))) throw new DuplicatedError("O Exercicio ja existe", exercise);
  }

  public void removeExercise(Exercise exercise) {
    exercises.remove(exercise);
  }


}
