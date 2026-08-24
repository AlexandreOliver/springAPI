package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.application.usecases.*;
import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import br.com.alex.springAPI.infrastructure.http.request.ExerciseCreate;
import br.com.alex.springAPI.infrastructure.http.request.ExercisePatch;

import br.com.alex.springAPI.exception.NotFoundExpection;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

  private final CreateExerciseUseCase createUseCase;
  private final UpdateExerciseUseCase updateUseCase;
  private final DeleteExerciseUseCase deleteUseCase;
  private final GetByIdExerciseUseCase getByIdUseCase;
  private final FindAllExerciseUseCase findAllExerciseUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ExerciseOutput> GET() {

    return this.findAllExerciseUseCase.execute();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ExerciseOutput POST(@Valid @RequestBody ExerciseCreate dataInput) {

    return this.createUseCase.execute(dataInput.toInput());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void DELETE(@PathVariable UUID id) throws NotFoundExpection {

    this.deleteUseCase.execute(new ExerciseId(id));
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void PATCH(@Valid @RequestBody ExercisePatch dataInput, @PathVariable UUID id) throws NotFoundExpection {

    this.updateUseCase.execute(dataInput.toInput(), new ExerciseId(id));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ExerciseOutput GET_ID(@PathVariable UUID id) {

    return this.getByIdUseCase.execute(new ExerciseId(id));
  }
}
