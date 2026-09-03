package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

public interface IExerciseRepository extends IRepositoryDomain<Exercise, ExerciseId> { }
