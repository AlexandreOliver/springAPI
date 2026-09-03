package br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces;

import br.com.alex.springAPI.infrastructure.persistence.jpa.projections.WorkoutDetailProjection;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.WorkoutEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.UUID;

public interface IWorkoutEntityRepository extends JpaRepository<WorkoutEntity, UUID> {

  Page<WorkoutEntity> findByStudent_Id(UUID id, Pageable pageable);

  @Query("SELECT w FROM WorkoutEntity w LEFT JOIN FETCH w.exercises WHERE w.student.id = :id")
  Page<WorkoutEntity> findByStudent_IdWithExercise(UUID id, Pageable pageable);

  @Query(value = """
    SELECT
        wE
    FROM WorkoutEntity wE
    WHERE wE.name LIKE %:query% OR wE.objective LIKE %:query%
""",
  countQuery = """
    SELECT 
        COUNT(wE)
    FROM WorkoutEntity wE
    WHERE wE.name LIKE %:query% OR wE.objective LIKE %:query%
      """
  )
  Page<WorkoutEntity> findAllWithQuery(String query, Pageable pageable);

  @Query("""
    SELECT
        wE.id,
        e
    FROM
        WorkoutEntity wE
    JOIN
        wE.exercises e
    WHERE
        wE.id in :workoutsId
""")
  Set<Object[]> findExerciseByWorkoutIds(Set<UUID> workoutsId);

  @Query(value = """
    SELECT
        wE.id AS id,
        wE.name AS name,
        wE.objective AS objective,
        s.name AS studentIdName
    FROM
        WorkoutEntity wE
    JOIN
        wE.student s
""",
      countQuery = """
    SELECT COUNT(wE)
    FROM WorkoutEntity wE
    JOIN wE.student s
""")
  Page<WorkoutDetailProjection> findAllWorkoutDetail(Pageable pageable);
}
