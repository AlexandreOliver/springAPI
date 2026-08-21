package br.com.alex.springAPI.controller;

import br.com.alex.springAPI.controller.dto.request.WorkoutRequestCreate;
import br.com.alex.springAPI.handler.OnlyMessageResponse;
import br.com.alex.springAPI.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

  private final WorkoutService workoutService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OnlyMessageResponse POST(@Valid @RequestBody final WorkoutRequestCreate dto) {
    this.workoutService.create(dto);

    return new OnlyMessageResponse("Criado com sucesso");
  }



  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void POST(@PathVariable final Long id) {
    this.workoutService.delete(id);

  }

}
