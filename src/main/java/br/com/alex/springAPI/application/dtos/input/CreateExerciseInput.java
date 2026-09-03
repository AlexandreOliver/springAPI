package br.com.alex.springAPI.application.dtos.input;

public record CreateExerciseInput(
    String name,
    String grupoMuscular,
    String equipament,
    int difficultLevel
) { }
