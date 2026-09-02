package br.com.alex.springAPI.infrastructure.http.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "Formato de resposta de erro")
public sealed class ErrorResponse permits ErrorValidationResponse {

    private String name;
    private String message;
    private Integer status;
    private String action;

}

