package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import java.util.Optional;

public interface IExerciseRepository extends IRepositoryDomain<Exercise, ExerciseId> {

  Pagination<Exercise> findAllWithQuery(PageApplication requestPage, Optional<String> query);

}
