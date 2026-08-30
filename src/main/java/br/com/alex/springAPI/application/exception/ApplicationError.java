package br.com.alex.springAPI.application.exception;

public abstract class ApplicationError extends RuntimeException {
  private final String source = "Application";
  private final String nameError;

  public ApplicationError(String nameError, String message) {
    super(message);

    this.nameError = nameError;
  }

  public ApplicationError(String message) {
    super(message);

    this.nameError = "ApplicationError";
  }
}


