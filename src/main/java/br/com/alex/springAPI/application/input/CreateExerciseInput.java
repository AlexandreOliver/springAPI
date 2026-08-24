package br.com.alex.springAPI.application.input;

public record CreateExerciseInput(
    String name,
    String grupoMuscular,
    String equipament,
    int difficultLevel
) { }
