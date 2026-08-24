package br.com.alex.springAPI.database.repository;

import br.com.alex.springAPI.database.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWorkoutRepository extends JpaRepository<Workout, Long> {

  List<Workout> findByStudent_Id(Long id);
}
