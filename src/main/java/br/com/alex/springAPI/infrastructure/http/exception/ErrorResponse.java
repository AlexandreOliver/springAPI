package br.com.alex.springAPI.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public sealed class ErrorResponse permits ErrorValidationResponse {

    private String name;
    private String message;
    private Integer status;
    private String action;

}

