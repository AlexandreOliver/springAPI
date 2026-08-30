package br.com.alex.springAPI.infrastructure.http.request;

import br.com.alex.springAPI.application.input.CreatePhisicalAssessmentInput;
import br.com.alex.springAPI.domain.valueObjects.Price;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AssessmentRequestCreate(
    @NotNull @Min(0) BigDecimal preco,
    @NotNull @Min(0) BigDecimal altura,
    @NotNull @Min(0) BigDecimal percentBodyFat
) {
  public CreatePhisicalAssessmentInput toInput() {
    return new CreatePhisicalAssessmentInput(new Price(this.preco), this.altura.doubleValue(), this.percentBodyFat.doubleValue());
  }
}
