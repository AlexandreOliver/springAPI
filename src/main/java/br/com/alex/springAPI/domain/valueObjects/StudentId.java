package br.com.alex.springAPI.domain.valueObjects;

import org.springframework.util.Assert;

import java.util.UUID;

public record StudentId(UUID uuid) {

  public StudentId {
    Assert.notNull(uuid, "O id nao pode ser nulo, precisa ser uma UUID");
  }

  public StudentId() {
    this(UUID.randomUUID());
  }
}

