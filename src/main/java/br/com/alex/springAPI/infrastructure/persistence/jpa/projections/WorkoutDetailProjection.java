package br.com.alex.springAPI.infrastructure.persistence.jpa.projections;

import java.util.UUID;

public interface WorkoutDetailProjection {
  UUID getId();
  String getName();
  String getObjective();
  String getStudentIdName();
}
