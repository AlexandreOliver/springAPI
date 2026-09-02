package br.com.alex.springAPI.domain.interfaces;

import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.Workout;

import br.com.alex.springAPI.domain.valueObjects.WorkoutDetail;
import br.com.alex.springAPI.domain.valueObjects.Pagination;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IWorkoutRepository extends IRepositoryDomain<Workout, WorkoutId> {

  List<Workout> findByStudentId(StudentId id, boolean includeExercise);

  Pagination<WorkoutDetail> findAllWorkoutDetail(int page, int size);

  Map<WorkoutId, Set<Exercise>> findAllExerciseByWorkoutIds(Set<WorkoutId> ids);
}
