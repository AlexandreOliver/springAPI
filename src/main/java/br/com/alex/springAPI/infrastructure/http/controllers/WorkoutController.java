package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.dtos.input.FindAllCommand;
import br.com.alex.springAPI.application.dtos.input.PageRequestApplication;
import br.com.alex.springAPI.application.dtos.output.WorkoutDetailOutput;
import br.com.alex.springAPI.application.dtos.output.WorkoutOutput;
import br.com.alex.springAPI.application.usecases.CreateWorkoutUseCase;
import br.com.alex.springAPI.application.usecases.DeleteWorkoutUseCase;
import br.com.alex.springAPI.application.usecases.FindAllWorkoutUseCase;
import br.com.alex.springAPI.application.usecases.GetWorkoutDetailUseCase;
import br.com.alex.springAPI.domain.valueObjects.Pagination;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;
import br.com.alex.springAPI.infrastructure.http.exception.NotFoundException;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.infrastructure.http.request.WorkoutCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/workout")
@RequiredArgsConstructor
@Tag(name = "Workout", description = "Endpoints para gerenciamento de treinos")
public class WorkoutController {

  private final CreateWorkoutUseCase createWorkoutUseCase;
  private final DeleteWorkoutUseCase deleteUseCase;
  private final GetWorkoutDetailUseCase workoutDetailUseCase;
  private final FindAllWorkoutUseCase allWorkoutUseCase;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um novo treino", description = "Cria um novo treino para um aluno específico")
  public OnlyMessageResponse POST_WORKOUT(@Valid @RequestBody WorkoutCreate workoutCreate, @RequestParam UUID studentid) {

    try {
      this.createWorkoutUseCase.execute(workoutCreate.toInput(studentid));
    } catch (NotFoundError e) {
      throw new NotFoundException(e.getMessage(), Optional.of("Corrija o Requisição"));
    }

    return new OnlyMessageResponse("Treino criado com Sucesso");

  }

  @GetMapping("/page/{page}/size/{size}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém treinos", description = "Retorna uma lista paginada de treinos")
  public Pagination<WorkoutOutput> GET_WORKOUT(
      @PathVariable(required = false) int page,
      @PathVariable(required = false) int size,
      @Parameter(name = "query", description = "Parâmetro de pesquisa opcional para filtrar os treinos")
      @RequestParam(required = false) Optional<String> query) {

    return this.allWorkoutUseCase.execute(new FindAllCommand(page, size, query.orElse(null)));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Deleta um treino", description = "Deleta um treino específico pelo ID")
  public void DELETE(@PathVariable UUID id) {
    this.deleteUseCase.execute(new WorkoutId(id));
  }


  @GetMapping("/detail")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém detalhes de treinos", description = "Retorna uma lista paginada de detalhes de treinos")
  public Pagination<WorkoutDetailOutput> GET_DETAIL(@RequestParam(defaultValue = "1", required = false) int page, @RequestParam(defaultValue = "10", required = false) int size) {

    return this.workoutDetailUseCase.execute(new PageRequestApplication(page, size));
  }
}
