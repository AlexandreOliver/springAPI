package br.com.alex.springAPI.domain.valueObjects;

import org.springframework.util.Assert;

import java.util.UUID;

public record ExerciseId(UUID id) {

  public ExerciseId {
    Assert.notNull(id, "O id nao pode ser nulo, precisa ser uma UUID");
  }

  public ExerciseId() {
    this(UUID.randomUUID());
  }
}
