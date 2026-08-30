package br.com.alex.springAPI.application.output;

import br.com.alex.springAPI.controller.dto.response.AssessmentResponse;
import lombok.Builder;

@Builder
public record StudentWithAssessmentOutput(
    Long Id,
    String name,
    String email,
    AssessmentResponse assessment
) { }

