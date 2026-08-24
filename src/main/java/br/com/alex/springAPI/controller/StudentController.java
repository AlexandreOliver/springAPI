package br.com.alex.springAPI.controller;


import br.com.alex.springAPI.controller.dto.request.StudentRequestCreate;
import br.com.alex.springAPI.controller.dto.response.AssessmentResponse;
import br.com.alex.springAPI.controller.dto.response.StudentResponseSummary;
import br.com.alex.springAPI.controller.dto.response.WorkoutResponseWithoutStudent;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OnlyMessageResponse POST(@Valid @RequestBody StudentRequestCreate dto) {

    this.service.create(dto);

     return new OnlyMessageResponse("Aluno criado com sucesso");
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<StudentResponseSummary> GET() {

    return this.service.listAll();
  }

  @GetMapping("/{id}/assessment")
  @ResponseStatus(HttpStatus.OK)
  public AssessmentResponse GETAssessment(@PathVariable Long id) {

    return this.service.getAssessment(id);
  }

  @GetMapping("/{id}/workouts")
  @ResponseStatus(HttpStatus.OK)
  public List<WorkoutResponseWithoutStudent> GETWorkouts(@PathVariable Long id) {

    return this.service.getWorkouts(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void DELETE(@PathVariable Long id) {

    this.service.delete(id);
  }

}
