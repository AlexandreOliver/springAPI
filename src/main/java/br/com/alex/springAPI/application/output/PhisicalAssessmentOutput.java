package br.com.alex.springAPI.application.output;

import br.com.alex.springAPI.domain.PhisicialAssessment;

public record PhisicalAssessmentOutput(
    String id,
    String preco,
    double altura,
    double percentBodyFat
)
{
  public static PhisicalAssessmentOutput of(PhisicialAssessment phisicialAssessment) {
    return new PhisicalAssessmentOutput(
        phisicialAssessment.getId().uuid().toString(),
        phisicialAssessment.getPreco().amount().toString(),
        phisicialAssessment.getAltura(), phisicialAssessment.getPercentBodyFat());
  }
}
