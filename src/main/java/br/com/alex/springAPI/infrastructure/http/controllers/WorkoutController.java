package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.usecases.CreateWorkoutUseCase;
import br.com.alex.springAPI.application.usecases.DeleteWorkoutUseCase;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;
import br.com.alex.springAPI.infrastructure.http.exception.NotFoundExpection;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.infrastructure.http.request.WorkoutCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("v1/workout")
@RequiredArgsConstructor
public class WorkoutController {

  private final CreateWorkoutUseCase createWorkoutUseCase;
  private final DeleteWorkoutUseCase deleteUseCase;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public OnlyMessageResponse POST_WORKOUT(@Valid @RequestBody WorkoutCreate workoutCreate, @RequestParam UUID studentid) {

    try {
      this.createWorkoutUseCase.execute(workoutCreate.toInput(studentid));
    } catch (NotFoundError e) {
      throw new NotFoundExpection(e.getMessage(), Optional.of("Corrija o Requisição"));
    }

    return new OnlyMessageResponse("Treino criado com Sucesso");

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void DELETE(@PathVariable UUID id) {
    this.deleteUseCase.execute(new WorkoutId(id));
  }
}
