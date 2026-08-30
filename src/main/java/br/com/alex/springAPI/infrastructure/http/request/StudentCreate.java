package br.com.alex.springAPI.infrastructure.http.request;

import br.com.alex.springAPI.application.input.CreateStudentInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentCreate(
    @NotBlank @Size(min = 2, max = 25) String name,
    @NotBlank @Email String email
) {
  public CreateStudentInput toInput() {
    return new CreateStudentInput(this.name, this.email);
  }
}

