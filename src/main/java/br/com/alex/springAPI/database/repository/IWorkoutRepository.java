package br.com.alex.springAPI.database.repository;

import br.com.alex.springAPI.database.model.Workout;
import br.com.alex.springAPI.database.projetion.WorkoutsOwnStudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.ProjectedPayload;

import java.util.List;

public interface IWorkoutRepository extends JpaRepository<Workout, Long> {

  List<Workout> findByStudent_Id(Long id);
}
