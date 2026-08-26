package br.com.alex.springAPI.application.input;

public record CreatePhisicalAssessmentInput(
    int preco,
    double altura,
    double percentBodyFat
) { }


