package br.com.alex.springAPI.application.exception;

public class NotFoundError extends ApplicationError {
  
  public NotFoundError(String message) {
    super("NotFound", message);
  }
}
