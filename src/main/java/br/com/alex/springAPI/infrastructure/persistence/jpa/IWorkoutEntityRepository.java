package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IWorkoutEntityRepository extends JpaRepository<WorkoutEntity, UUID> {

  List<WorkoutEntity> findByStudent_Id(UUID id);

  @Query("SELECT w FROM WorkoutEntity w LEFT JOIN FETCH w.exercises WHERE w.student.id = :id")
  List<WorkoutEntity> findByStudent_IdWithExercise(UUID id);
}
