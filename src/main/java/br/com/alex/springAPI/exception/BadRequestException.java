package br.com.alex.springAPI.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

  private String name;
  private Integer statusCode;

  public BadRequestException(String message) {

    super(message);

    this.name = "BadRequestError";
    this.statusCode = HttpStatus.BAD_REQUEST.value();
  }
}
