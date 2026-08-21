package br.com.alex.springAPI.controller.dto.response;

import lombok.Builder;

@Builder
public record StudentResponse(
    Long Id,
    String name,
    String email,
    AssessmentResponse assessment
) { }

