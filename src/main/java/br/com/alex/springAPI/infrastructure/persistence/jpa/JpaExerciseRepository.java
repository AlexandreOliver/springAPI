package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.ExerciseEntity;
import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IExerciseEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaExerciseRepository implements IExerciseRepository {
  private final IExerciseEntityRepository exerciseEntityRepository;

  @Override
  public Exercise save(Exercise exercise) {
    return this.exerciseEntityRepository.save(ExerciseEntity.from(exercise)).toDomain();
  }

  @Override
  public Pagination<Exercise> findAll(PageApplication requestPage) {

    Page<ExerciseEntity> exerciseEntities = this.exerciseEntityRepository.findAll(
        PageRequest.of(requestPage.page() - 1, requestPage.size())
    );

    Pagination<Exercise> pagination = Pagination.<Exercise>builder()
        .contents(exerciseEntities.getContent().stream().map(ExerciseEntity::toDomain).toList())
        .pageCurrent(exerciseEntities.getNumber() + 1)
        .pageSize(exerciseEntities.getSize())
        .totalElements((int) exerciseEntities.getTotalElements())
        .totalPages(exerciseEntities.getTotalPages())
        .build();

    return pagination;
  }

  @Override
  public Pagination<Exercise> findAllWithQuery(PageApplication requestPage, Optional<String> query) {

    Page<ExerciseEntity> exerciseEntities = this.exerciseEntityRepository.findAllWithQuery(
        PageRequest.of(requestPage.page() - 1, requestPage.size()),
        query.orElse("")
    );

    Pagination<Exercise> pagination = Pagination.<Exercise>builder()
        .contents(exerciseEntities.getContent().stream().map(ExerciseEntity::toDomain).toList())
        .pageCurrent(exerciseEntities.getNumber() + 1)
        .pageSize(exerciseEntities.getSize())
        .totalElements((int) exerciseEntities.getTotalElements())
        .totalPages(exerciseEntities.getTotalPages())
        .build();

    return pagination;
  }

  @Override
  public Optional<Exercise> findById(ExerciseId id) {

    Optional<Exercise> result;

    try {
      result = Optional.ofNullable(this.exerciseEntityRepository.getReferenceById(id.id()).toDomain());
    } catch (EntityNotFoundException ex) {
      return Optional.empty();
    }

    return result;
  }

  @Override
  public void delete(ExerciseId id) {
    this.exerciseEntityRepository.deleteById(id.id());
  }
}
