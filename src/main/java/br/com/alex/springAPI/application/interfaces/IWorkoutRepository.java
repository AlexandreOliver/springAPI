package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.Workout;

import br.com.alex.springAPI.application.dtos.WorkoutDetail;
import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IWorkoutRepository extends IRepositoryDomain<Workout, WorkoutId> {

  Pagination<Workout> findByStudentId(StudentId id, boolean includeExercise, PageApplication requestPage);

  Pagination<WorkoutDetail> findAllWorkoutDetail(int page, int size);

  Map<WorkoutId, Set<Exercise>> findAllExerciseByWorkoutIds(Set<WorkoutId> ids);

  Pagination<Workout> findAllWithQuery(PageApplication requestPage, Optional<String> query);
}
