package br.com.alex.springAPI.infrastructure.http.controllers;

import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.application.exception.DuplicatedAssessmentError;
import br.com.alex.springAPI.application.exception.DuplicatedEmail;
import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.output.AssessmentOutput;
import br.com.alex.springAPI.application.output.StudentOutput;
import br.com.alex.springAPI.application.usecases.*;

import br.com.alex.springAPI.infrastructure.http.exception.UnprocessableEntityException;
import br.com.alex.springAPI.infrastructure.http.request.AssessmentRequestCreate;
import br.com.alex.springAPI.infrastructure.http.response.StudentSummaryResponse;
import br.com.alex.springAPI.infrastructure.http.exception.NotFoundExpection;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.infrastructure.http.request.StudentCreate;

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
public class StudentController {

  private final CreateStudentUseCase createUseCase;
  private final FindAllStudentUseCase getAllUseCase;
  private final GetByIdStudentUseCase getByIdUseCase;
  private final DeleteStudentUseCase deleteUseCase;
  private final CreateAssessmentUseCase createAssessmentUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
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
 public ResponseEntity<?> GET_ALL(@RequestParam(required = false) String include) {

   if ("assessment".equalsIgnoreCase(include)) {
     List<StudentOutput> students = this.getAllUseCase.execute(true);

     return ResponseEntity.ok(students);
   } else {
      List<StudentSummaryResponse> studentSummaryOutputs = this.getAllUseCase.execute(false).stream().map(StudentSummaryResponse::of).toList();

      return ResponseEntity.ok(studentSummaryOutputs);
   }
 }

  @GetMapping("/{id}/assessment")
  @ResponseStatus(HttpStatus.OK)
  public AssessmentOutput GET_ASSESSMENT(@PathVariable UUID id) {
   AssessmentOutput assessment;

   try {
     assessment = this.getByIdUseCase.execute(new StudentId(id), true).assessment();
   } catch (NotFoundError e) {
     throw new NotFoundExpection(e.getMessage(), Optional.of("Forneça outro Id"));
   }

     if (assessment == null) {
       throw new NotFoundExpection("Esse aluno não possui um exame registrado",
           Optional.of("Registre um exame para ele"));
     }

     return assessment;
  }

  @PostMapping("/{id}/assessment")
  @ResponseStatus(HttpStatus.OK)
  public OnlyMessageResponse POST_ASSESSMENT(@PathVariable UUID id, @RequestBody AssessmentRequestCreate requestBody) {

   try {
     this.createAssessmentUseCase.execute(requestBody.toInput(), new StudentId(id));
   } catch (NotFoundError ex) {
     throw new NotFoundExpection(ex.getMessage(), Optional.of("Verifique o Id e tente novamente"));
   } catch (DuplicatedAssessmentError ex) {
     throw new UnprocessableEntityException(ex.getMessage(), "Você só pode criar um exame para quem não possui");
   }

    return new OnlyMessageResponse("Exame adicionado com sucesso");
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> GET_STUDENT(@PathVariable UUID id, @RequestParam(required = false) String include) {
   try {
      if ("assessment".equalsIgnoreCase(include)) {
        return ResponseEntity.ok(this.getByIdUseCase.execute(new StudentId(id), true));
      } else {
        StudentOutput studentOutput = this.getByIdUseCase.execute(new StudentId(id), false);

        return ResponseEntity.ok(StudentSummaryResponse.of(studentOutput));
      }

    } catch (NotFoundError ex) {
      throw new NotFoundExpection(ex.getMessage(), Optional.of("Crie."));
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void DELETE(@PathVariable UUID id) {
    this.deleteUseCase.execute(new StudentId(id));
  }
 }
