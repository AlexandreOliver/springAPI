package br.com.alex.springAPI.controller.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AssessmentResponse(
    Long id,
    BigDecimal preco,
    BigDecimal altura,
    BigDecimal percentBodyFat
) { }
