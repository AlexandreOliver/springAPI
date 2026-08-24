package br.com.alex.springAPI.domain;

import br.com.alex.springAPI.domain.valueObjects.ExerciseId;
import lombok.*;

import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Exercise {
  private ExerciseId id;
  private String name;
  private String grupoMuscular;
  private String equipament;
  private int difficultLevel;


  public void update(Optional<String> name,
                     Optional<String> grupoMuscular,
                     Optional<String> equipament,
                     Optional<Integer> difficultLevel) {

    name.ifPresent(this::setName);
    grupoMuscular.ifPresent(this::setGrupoMuscular);
    equipament.ifPresent(this::setEquipament);
    difficultLevel.ifPresent(this::setDifficultLevel);

  }
}
