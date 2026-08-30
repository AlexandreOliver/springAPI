package br.com.alex.springAPI.domain.interfaces;

import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import java.util.List;

public interface IWorkoutRepository extends IRepositoryDomain<Workout, WorkoutId> {

  List<Workout> findByStudentId(StudentId id, boolean includeExercise);
}
