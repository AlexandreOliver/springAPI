package br.com.alex.springAPI.infrastructure.persistence;

import br.com.alex.springAPI.infrastructure.persistence.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface IExerciseEntityRepository extends JpaRepository<ExerciseEntity, UUID> {


  @Query(value = "SELECT e FROM ExerciseEntity e WHERE e.grupoMuscular = LOWER(:grupoMuscular)")
  List<ExerciseEntity> findAllbyGrupoMuscular(@Param("grupoMuscular") String grupoMuscular);

}
