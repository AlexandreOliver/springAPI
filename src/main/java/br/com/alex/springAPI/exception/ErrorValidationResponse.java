package br.com.alex.springAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Getter
public final class ErrorValidationResponse extends ErrorResponse {

    private Map<String, String> fields;
    
}
