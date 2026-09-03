package br.com.alex.springAPI.application.dtos.input;

import br.com.alex.springAPI.domain.valueObjects.Price;

public record CreatePhisicalAssessmentInput(
    Price preco,
    double altura,
    double percentBodyFat
) { }


