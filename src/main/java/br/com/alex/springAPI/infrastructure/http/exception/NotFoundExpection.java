package br.com.alex.springAPI.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
public class NotFoundExpection extends RuntimeException{

    private final String name;
    private final String action;
    private final HttpStatus statusCode;

    public NotFoundExpection(String message, Optional<String> action) {
        super(message);

        this.name = "NotFoundError";
        this.action = action.orElse("Verifique se há erros nos parametros de busca");
        this.statusCode = HttpStatus.NOT_FOUND;
    }
}

