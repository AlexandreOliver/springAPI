package br.com.alex.springAPI.domain.exception;

import lombok.Getter;

@Getter
public abstract class ErrorDomain extends RuntimeException {
  private final String nameError;
  private final String source = "Domain";

  protected ErrorDomain(String nameError, String message) {
    super(message);
    this.nameError = nameError;

  }

  protected ErrorDomain(String message) {
    super(message);

    this.nameError = "ErrorDomain";
  }
}

