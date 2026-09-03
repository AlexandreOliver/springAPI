package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.application.interfaces.IExerciseRepository;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.ExerciseEntity;
import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IExerciseEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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
  public List<Exercise> findAll() {
    return this.exerciseEntityRepository.findAll().stream().map(ExerciseEntity::toDomain).toList();
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
