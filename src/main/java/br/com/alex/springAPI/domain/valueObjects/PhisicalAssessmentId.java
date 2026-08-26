package br.com.alex.springAPI.domain.valueObjects;

import org.springframework.util.Assert;

import java.util.UUID;

public record PhisicalAssessmentId(UUID uuid) {

  public PhisicalAssessmentId {
    Assert.notNull(uuid, "Forneça um uuid");
  }

  public PhisicalAssessmentId() {
    this(UUID.randomUUID());
  }

}
