package br.com.alex.springAPI.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AssessmentRequestCreate(
    @NotNull @Min(0) Long alunoId,
    @NotNull @Min(0) BigDecimal preco,
    @NotNull @Min(0) BigDecimal altura,
    @NotNull @Min(0) BigDecimal percentBodyFat) {
}
