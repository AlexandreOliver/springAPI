package br.com.alex.springAPI.database.projetion;

import br.com.alex.springAPI.controller.dto.response.ExerciseResponse;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.ProjectedPayload;

import java.util.Set;

@ProjectedPayload
public interface WorkoutsOwnStudentProjection extends ProjectionFactory {
  Long getTd();

  String getName();

  String getObjective();

  Set<ExerciseResponse> getExercises();
}
