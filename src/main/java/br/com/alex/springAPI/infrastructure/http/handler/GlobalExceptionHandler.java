package br.com.alex.springAPI.infrastructure.http.handler;

import br.com.alex.springAPI.application.exception.DuplicatedAssessmentError;
import br.com.alex.springAPI.infrastructure.http.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundExpection.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundExpection ex) {

        ErrorResponse response = ErrorResponse
                .builder()
                .message(ex.getMessage())
                .status(ex.getStatusCode().value())
                .name(ex.getName())
                .action(ex.getAction())
                .build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception ex) {

        ErrorResponse response = ErrorResponse
                .builder()
                .name("InternalServerError")
                .message("Algum erro desconhecido aconteceu")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .action("Tente novamente.")
                .build();

        log.error("ERROR: ", ex);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidationResponse> handlerException(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();


            fieldErrors.put(fieldName, errorMessage);
        });

        ErrorValidationResponse response = ErrorValidationResponse
            .builder()
            .name("ValidationError")
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Alguns campos possuem valores não permitidos")
            .action("Forneça os dados corretos.")
            .fields(fieldErrors)
            .build();

        log.error("ERROR: ", ex);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerException(BadRequestException ex) {

        ErrorResponse response = ErrorResponse
            .builder()
            .name(ex.getName())
            .message(ex.getMessage())
            .status(ex.getStatusCode())
            .build();

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerException(UnprocessableEntityException ex) {

        ErrorResponse response = ErrorResponse
            .builder()
            .name(ex.getName())
            .message(ex.getMessage())
            .status(ex.getStatusCode())
            .action(ex.getAction())
            .build();

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }
}
