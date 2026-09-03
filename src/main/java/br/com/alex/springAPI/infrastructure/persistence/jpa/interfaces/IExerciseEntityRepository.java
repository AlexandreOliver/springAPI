package br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.ExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface IExerciseEntityRepository extends JpaRepository<ExerciseEntity, UUID> {

  @Query(
      value = """
          SELECT
              s
          FROM
              ExerciseEntity s
          WHERE
              LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
          """,
      countQuery = """ 
          SELECT
              COUNT(s)
          FROM
              ExerciseEntity s
          WHERE
              LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
          """
  )
  Page<ExerciseEntity> findAllWithQuery(Pageable pageable, String query);

  Page<ExerciseEntity> findAll(Pageable pageable);

  @Query(value = "SELECT e FROM ExerciseEntity e WHERE e.grupoMuscular = LOWER(:grupoMuscular)")
  List<ExerciseEntity> findAllbyGrupoMuscular(@Param("grupoMuscular") String grupoMuscular);

}
