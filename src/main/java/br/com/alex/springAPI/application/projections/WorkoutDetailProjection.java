package br.com.alex.springAPI.application.projections;

import java.util.UUID;

public interface WorkoutDetailProjection {
  UUID getId();
  String getName();
  String getObjective();
  String getStudentIdName();
}
