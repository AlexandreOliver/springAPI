package br.com.alex.springAPI.domain.valueObjects;

import org.springframework.util.Assert;

import java.util.UUID;

public record WorkoutId(UUID id) {

  public WorkoutId {
    Assert.notNull(id, "O id nao pode ser nulo, precisa ser uma UUID");
  }

  public WorkoutId() {
    this(UUID.randomUUID());
  }
}
