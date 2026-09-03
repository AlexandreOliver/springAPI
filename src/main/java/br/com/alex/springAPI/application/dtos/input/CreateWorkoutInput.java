package br.com.alex.springAPI.application.dtos.input;

import br.com.alex.springAPI.domain.valueObjects.StudentId;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record CreateWorkoutInput(
    String name,
    String objective,
    StudentId studentId,
    Set<UUID> exercises
) { }
