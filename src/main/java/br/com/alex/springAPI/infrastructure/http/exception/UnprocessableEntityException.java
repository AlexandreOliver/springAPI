package br.com.alex.springAPI.infrastructure.http.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnprocessableEntityException extends RuntimeException {

  private String name;
  private Integer statusCode;
  private String action;

  public UnprocessableEntityException(String message, String action) {

    super(message);

    this.name = "UnprocessableEntityError";
    this.statusCode = HttpStatus.UNPROCESSABLE_CONTENT.value();
    this.action = action;
  }
}
