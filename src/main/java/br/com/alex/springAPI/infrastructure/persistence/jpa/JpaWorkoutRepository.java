package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.domain.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.WorkoutEntity;

import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IWorkoutEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaWorkoutRepository implements IWorkoutRepository {
  private final IWorkoutEntityRepository workoutEntityRepository;

  @PersistenceContext
  private EntityManager em;


  @Override
  @Transactional
  public Workout save(Workout entity) {
    WorkoutEntity workoutEntity = WorkoutEntity.from(entity);

    workoutEntity.setStudent(em.getReference(StudentEntity.class, entity.getStudentId().uuid()));

    em.persist(workoutEntity);

    return entity;
  }

  @Override
  public List<Workout> findAll() {
    return List.of();
  }

  @Override
  public Optional<Workout> findById(WorkoutId id) {
    return Optional.empty();
  }

  @Override
  public void delete(WorkoutId id) {
    this.workoutEntityRepository.deleteById(id.id());
  }

  @Override
  public List<Workout> findByStudentId(StudentId id, boolean includeExercise) {
    List<Workout> workouts;

    if (includeExercise) {
      workouts = this.workoutEntityRepository.findByStudent_IdWithExercise(id.uuid()).stream().map(WorkoutEntity::toDomain).toList();
    } else {
      workouts = this.workoutEntityRepository.findByStudent_Id(id.uuid()).stream().map(WorkoutEntity::toDomain).toList();
    }

    return workouts;
  }
}
