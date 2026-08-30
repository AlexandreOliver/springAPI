package br.com.alex.springAPI.domain.exception;

import lombok.Getter;

@Getter
public class DuplicatedError extends ErrorDomain {
  private Object resource;

  public DuplicatedError(String message, Object resource) {
    super("DuplicatedError", message);

    this.resource = resource;
  }

  public DuplicatedError(String message) {
    super("DuplicatedError", message);
  }
}
