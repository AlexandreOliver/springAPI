package br.com.alex.springAPI.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRequestCreate(
    @NotBlank @Size(min = 2, max = 25) String name,
    @NotBlank @Email String email
) { }

