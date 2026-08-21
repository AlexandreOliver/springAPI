package br.com.alex.springAPI.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicatedAssessmentError extends RuntimeException {
    private final HttpStatus statusCode;
    private final String name;
    
    public DuplicatedAssessmentError(String message) {
        super(message);
        
        this.name = "DuplicatedAssessmentError";
        this.statusCode = HttpStatus.BAD_REQUEST;
    }
}
