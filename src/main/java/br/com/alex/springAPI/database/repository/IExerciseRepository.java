package br.com.alex.springAPI.database.repository;

import br.com.alex.springAPI.database.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IExerciseRepository extends JpaRepository<Exercise, Long> {


  @Query(value = "SELECT e FROM Exercise e WHERE e.grupoMuscular = LOWER(:grupoMuscular)")
  List<Exercise> findAllbyGrupoMuscular(@Param("grupoMuscular") String grupoMuscular);

}
