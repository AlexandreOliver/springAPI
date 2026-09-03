package br.com.alex.springAPI.infrastructure.persistence.jpa;


import br.com.alex.springAPI.application.input.PageRequestApplication;
import br.com.alex.springAPI.domain.valueObjects.WorkoutDetail;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.application.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.Pagination;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.ExerciseEntity;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.WorkoutEntity;

import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IWorkoutEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

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
  public Pagination<Workout> findAll(PageRequestApplication requestPage, Optional<String> query) {

    var pageInDb = this.workoutEntityRepository.findAllWithFilter(query.orElse(""), PageRequest.of(requestPage.page() - 1, requestPage.size()));

    Pagination<Workout> workoutPagination = Pagination.<Workout>builder()
        .pageSize(pageInDb.getSize())
        .totalPages(pageInDb.getTotalPages())
        .totalElements((int) pageInDb.getTotalElements())
        .pageCurrent(pageInDb.getNumber() + 1)
        .contents(pageInDb.getContent().stream().map(WorkoutEntity::toDomain).toList())
        .build();

    return workoutPagination;
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

  @Override
  public Pagination<WorkoutDetail> findAllWorkoutDetail(int page, int size) {

    var pageInDb = this.workoutEntityRepository
        .findAllWorkoutDetail(PageRequest.of(page - 1, size))
        .map(w -> WorkoutDetail.builder()
            .workoutId(new WorkoutId(w.getId()))
            .workoutName(w.getName())
            .objective(w.getObjective())
            .studentName(w.getStudentIdName())
            .build());

    Pagination<WorkoutDetail> workoutDetailProjection = Pagination.<WorkoutDetail>builder()
        .pageSize(pageInDb.getSize())
        .totalPages(pageInDb.getTotalPages())
        .totalElements((int) pageInDb.getTotalElements())
        .pageSize(pageInDb.getSize())
        .pageCurrent(pageInDb.getNumber() + 1)
        .contents(pageInDb.getContent())
        .build();
        
    return workoutDetailProjection;
  }

  @Override
  public Map<WorkoutId, Set<Exercise>> findAllExerciseByWorkoutIds(Set<WorkoutId> ids) {
    Map<WorkoutId, Set<Exercise>> exercisesMap;

    Set<Object[]> exercises = this.workoutEntityRepository.findExerciseByWorkoutIds(ids.stream().map(WorkoutId::id).collect(Collectors.toSet()));

    exercisesMap = exercises.stream().collect(
        Collectors.groupingBy(
            row -> new WorkoutId((UUID) row[0]),
            Collectors.mapping(row -> ((ExerciseEntity) row[1]).toDomain(), Collectors.toSet())
        )
    );

    return exercisesMap;
  }
}
