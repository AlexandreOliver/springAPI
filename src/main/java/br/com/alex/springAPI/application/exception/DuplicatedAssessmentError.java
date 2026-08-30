package br.com.alex.springAPI.application.exception;

import br.com.alex.springAPI.application.input.CreatePhisicalAssessmentInput;
import lombok.Getter;

@Getter
public class DuplicatedAssessmentError extends ApplicationError {
    public CreatePhisicalAssessmentInput input;
    
    public DuplicatedAssessmentError(String message, CreatePhisicalAssessmentInput input) {
        super("DuplicatedAssessmentError", message);

        this.input = input;

    }
}
