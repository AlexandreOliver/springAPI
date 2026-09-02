package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.application.usecases.*;

import br.com.alex.springAPI.domain.valueObjects.ExerciseId;

import br.com.alex.springAPI.infrastructure.http.request.ExerciseCreate;
import br.com.alex.springAPI.infrastructure.http.request.ExercisePatch;
import br.com.alex.springAPI.infrastructure.http.exception.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/v1/exercise")
@RequiredArgsConstructor
@Tag(name = "Exercise", description = "Endpoints para gerenciamento de exercícios")
public class ExerciseController {

  private final CreateExerciseUseCase createUseCase;
  private final UpdateExerciseUseCase updateUseCase;
  private final DeleteExerciseUseCase deleteUseCase;
  private final GetByIdExerciseUseCase getByIdUseCase;
  private final FindAllExerciseUseCase findAllExerciseUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém todos os exercícios", description = "Retorna uma lista de todos os exercícios")
  public List<ExerciseOutput> GET() {

    return this.findAllExerciseUseCase.execute();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um novo exercício", description = "Cria um novo exercício com os dados fornecidos")
  public ExerciseOutput POST(@Valid @RequestBody ExerciseCreate dataInput) {

    return this.createUseCase.execute(dataInput.toInput());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Deleta um exercício", description = "Deleta um exercício específico pelo ID")
  public void DELETE(@PathVariable UUID id) throws NotFoundException {

    this.deleteUseCase.execute(new ExerciseId(id));
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Atualiza um exercício", description = "Atualiza um exercício específico pelo ID")
  public void PATCH(@Valid @RequestBody ExercisePatch dataInput, @PathVariable UUID id) throws NotFoundException {

    this.updateUseCase.execute(dataInput.toInput(), new ExerciseId(id));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém um exercício", description = "Retorna os dados de um exercício específico pelo ID")
  public ExerciseOutput GET_ID(@PathVariable UUID id) {

    return this.getByIdUseCase.execute(new ExerciseId(id));
  }
}
