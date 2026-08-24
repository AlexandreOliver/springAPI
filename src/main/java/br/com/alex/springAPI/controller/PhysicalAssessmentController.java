package br.com.alex.springAPI.controller;

import br.com.alex.springAPI.controller.dto.request.AssessmentRequestCreate;
import br.com.alex.springAPI.infrastructure.http.handler.OnlyMessageResponse;
import br.com.alex.springAPI.service.AssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/assessment")
@RequiredArgsConstructor
public class PhysicalAssessmentController {

  private final AssessmentService service;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OnlyMessageResponse create(@Valid @RequestBody AssessmentRequestCreate dto) {

    this.service.save(dto);

    return new OnlyMessageResponse("Exame registrado com sucesso");

  };
}
