package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.application.output.WorkoutOutput;
import br.com.alex.springAPI.application.exception.DuplicatedAssessmentError;
import br.com.alex.springAPI.application.exception.DuplicatedEmail;
import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.output.AssessmentOutput;
import br.com.alex.springAPI.application.output.StudentOutput;
import br.com.alex.springAPI.application.usecases.*;

import br.com.alex.springAPI.infrastructure.http.exception.BadRequestException;
import br.com.alex.springAPI.infrastructure.http.exception.UnprocessableEntityException;
import br.com.alex.springAPI.infrastructure.http.request.AssessmentRequestCreate;
import br.com.alex.springAPI.infrastructure.http.request.WorkoutCreate;
import br.com.alex.springAPI.infrastructure.http.response.StudentSummaryResponse;
import br.com.alex.springAPI.infrastructure.http.exception.NotFoundException;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.infrastructure.http.request.StudentCreate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Endpoints para gerenciamento de alunos")
public class StudentController {

  private final CreateStudentUseCase createUseCase;
  private final FindAllStudentUseCase getAllUseCase;
  private final GetByIdStudentUseCase getByIdUseCase;
  private final DeleteStudentUseCase deleteUseCase;
  private final CreateAssessmentUseCase createAssessmentUseCase;
  private final CreateWorkoutUseCase createWorkoutUseCase;
  private final GetWorkoutByStudentIdUseCase workoutByStudentIdUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um novo aluno", description = "Cria um novo aluno com os dados fornecidos")
  public OnlyMessageResponse create(@Valid @RequestBody StudentCreate dto) {
    try {
      this.createUseCase.execute(dto.toInput());
    } catch (DuplicatedEmail ex) {
      throw new UnprocessableEntityException(ex.getMessage(), "Forneça outro email");
    }

    return new OnlyMessageResponse("Aluno criado com sucesso");
  };

 @GetMapping
 @ResponseStatus(HttpStatus.OK)
 @Operation(summary = "Lista todos os alunos", description = "Retorna uma lista dos alunos")
 public ResponseEntity<?> GET_ALL(
     @Parameter(description = "Parâmetro opcional para incluir informações de avaliação")
     @RequestParam(required = false) String include
 ) {

   if ("assessment".equalsIgnoreCase(include)) {
     List<StudentOutput> students = this.getAllUseCase.execute(true);

     return ResponseEntity.ok(students);
   } else {
      List<StudentSummaryResponse> studentSummaryOutputs = this.getAllUseCase.execute(false).stream().map(StudentSummaryResponse::of).toList();

      return ResponseEntity.ok(studentSummaryOutputs);
   }
 }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém os dados de um aluno", description = "Retorna os dados de um aluno específico pelo ID")
  public ResponseEntity<?> GET_STUDENT(
      @PathVariable UUID id,
      @Parameter(description = "Parâmetro opcional para incluir informações de avaliação")
      @RequestParam(required = false) String include) {
    try {
      if ("assessment".equalsIgnoreCase(include)) {
        return ResponseEntity.ok(this.getByIdUseCase.execute(new StudentId(id), true));
      } else {
        StudentOutput studentOutput = this.getByIdUseCase.execute(new StudentId(id), false);

        return ResponseEntity.ok(StudentSummaryResponse.of(studentOutput));
      }

    } catch (NotFoundError ex) {
      throw new NotFoundException(ex.getMessage(), Optional.of("Crie."));
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Deleta um aluno", description = "Deleta um aluno específico pelo ID")
  public void DELETE(@PathVariable UUID id) {
    this.deleteUseCase.execute(new StudentId(id));
  }

  @GetMapping("/{id}/assessment")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém a avaliação de um aluno", description = "Retorna a avaliação de um aluno específico pelo ID")
  public AssessmentOutput GET_ASSESSMENT(@PathVariable UUID id) {
   AssessmentOutput assessment;

   try {
     assessment = this.getByIdUseCase.execute(new StudentId(id), true).assessment();
   } catch (NotFoundError e) {
     throw new NotFoundException(e.getMessage(), Optional.of("Forneça outro Id"));
   }

     if (assessment == null) {
       throw new NotFoundException("Esse aluno não possui um exame registrado",
           Optional.of("Registre um exame para ele"));
     }

     return assessment;
  }

  @PostMapping("/{id}/assessment")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Cria uma avaliação para o aluno", description = "Cria uma avaliação para o aluno específico pelo ID")
  public OnlyMessageResponse POST_ASSESSMENT(@PathVariable UUID id, @Valid @RequestBody AssessmentRequestCreate requestBody) {

   try {
     this.createAssessmentUseCase.execute(requestBody.toInput(), new StudentId(id));
   } catch (NotFoundError ex) {
     throw new NotFoundException(ex.getMessage(), Optional.of("Verifique o Id e tente novamente"));
   } catch (DuplicatedAssessmentError ex) {
     throw new UnprocessableEntityException(ex.getMessage(), "Você só pode criar um exame para quem não possui");
   }

    return new OnlyMessageResponse("Exame adicionado com sucesso");
  }

  @PostMapping("/{id}/workout")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um treino para o aluno", description = "Cria um treino para o aluno específico pelo ID")
  public OnlyMessageResponse POST_WORKOUT(@Valid @RequestBody WorkoutCreate workoutCreate, @PathVariable UUID id) {

   try {
     this.createWorkoutUseCase.execute(workoutCreate.toInput(id));
   } catch (NotFoundError e) {
     throw new NotFoundException(e.getMessage(), Optional.of("Corrija o Requisição"));
   }

   return new OnlyMessageResponse("Treino criado com Sucesso");

  }

  @GetMapping("/{id}/workout")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Obtém os treinos de um aluno", description = "Retorna uma lista de treinos de um aluno específico pelo ID")
  public List<WorkoutOutput> GET_WORKOUT(
      @PathVariable UUID id,
      @Parameter(description = "Parâmetro opcional para incluir informações dos exercícios do treino")
      @RequestParam(required = false) String include) {

   try {

     if ("exercises".equalsIgnoreCase(include)) {
       return this.workoutByStudentIdUseCase.execute(new StudentId(id), true);
     } else {
       return this.workoutByStudentIdUseCase.execute(new StudentId(id), false);
     }
   } catch (NotFoundError e) {
     throw new BadRequestException(e.getMessage(), "Corrija a request");
   }


  }

}
