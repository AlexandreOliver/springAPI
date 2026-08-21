package br.com.alex.springAPI.service;

import br.com.alex.springAPI.controller.dto.request.ExerciseRequestCreate;
import br.com.alex.springAPI.controller.dto.request.ExerciseRequestPatch;
import br.com.alex.springAPI.database.model.Exercise;
import br.com.alex.springAPI.database.repository.IExerciseRepository;
import br.com.alex.springAPI.exception.BadRequestException;
import br.com.alex.springAPI.exception.NotFoundExpection;
import br.com.alex.springAPI.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

  private final IExerciseRepository exerciseRepository;

  public List<Exercise> listAll() {
    return this.exerciseRepository.findAll();
  }

  public Exercise save(ExerciseRequestCreate exerciseDTO) {

    return this.exerciseRepository.save(Exercise
        .builder()
        .nome(exerciseDTO.nome().toLowerCase())
        .equipament(exerciseDTO.equipament().toLowerCase())
        .grupoMuscular(exerciseDTO.grupoMuscular().toLowerCase())
        .difficultLevel(exerciseDTO.difficultLevel())
        .build());
  }

  public void delete(Long id) throws NotFoundExpection {

    if (!this.exerciseRepository.existsById(id)) {
      throw new NotFoundExpection("Não existem registros para o id fornecido", Optional.of("Forneça outro id"));
    }

    this.exerciseRepository.deleteById((id));
  }

  public void update(ExerciseRequestPatch exerciseDTO, Long id) throws NotFoundExpection {

    validDtoNull(exerciseDTO);

    Exercise exerciseInDb = this.exerciseRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundExpection("Não existem registros para o id fornecido", Optional.of("Forneça outro id")));

    BeanUtils.copyProperties(exerciseDTO, exerciseInDb, ClassUtils.getNullPropertyNames(exerciseDTO));

    this.exerciseRepository.save(exerciseInDb);

  }

  private void validDtoNull(ExerciseRequestPatch dto) {
    if (dto.nome() == null &&
        dto.equipament() == null &&
        dto.grupoMuscular() == null &&
        dto.difficultLevel() == null) {

      throw new BadRequestException(
          "A requisição deve conter pelo menos um campo para atualização."
      );
    }
  }


}

