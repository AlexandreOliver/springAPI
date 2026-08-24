package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import java.util.List;
import java.util.Optional;

public interface IExerciseRepository {

  Exercise save(Exercise exercise);

  List<Exercise> findAll();

  Optional<Exercise> findById(ExerciseId id);

  void delete(ExerciseId id);

}
