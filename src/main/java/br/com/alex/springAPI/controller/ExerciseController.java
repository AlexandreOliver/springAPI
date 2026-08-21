package br.com.alex.springAPI.controller;

import br.com.alex.springAPI.controller.dto.request.ExerciseRequestCreate;
import br.com.alex.springAPI.controller.dto.request.ExerciseRequestPatch;
import br.com.alex.springAPI.database.model.Exercise;
import br.com.alex.springAPI.exception.NotFoundExpection;
import br.com.alex.springAPI.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

  private final ExerciseService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Exercise> GET() {

    return this.service.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Exercise POST(@Valid @RequestBody ExerciseRequestCreate dataInput) {
    
    return this.service.save(dataInput);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void DELETE(@PathVariable Long id) throws NotFoundExpection {

    this.service.delete(id);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void PATCH(@Valid @RequestBody ExerciseRequestPatch dataInput, @PathVariable Long id) throws NotFoundExpection {

    this.service.update(dataInput, id);
  }
}