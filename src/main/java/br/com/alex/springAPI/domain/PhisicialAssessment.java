package br.com.alex.springAPI.domain;

import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;

import br.com.alex.springAPI.domain.valueObjects.Price;
import lombok.*;

import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhisicialAssessment {
  private PhisicalAssessmentId id;
  private Price preco;
  private double altura;
  private double percentBodyFat;


  public void update(
      Optional<Price> preco,
      Optional<Double> altura,
      Optional<Double> percentBodyFat)
  {

    preco.ifPresent(p -> this.preco = p);
    altura.ifPresent(a -> this.altura = a);
    percentBodyFat.ifPresent(bd -> this.percentBodyFat = bd);

  }
}
