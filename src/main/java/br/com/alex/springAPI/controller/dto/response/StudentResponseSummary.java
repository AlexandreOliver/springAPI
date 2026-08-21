package br.com.alex.springAPI.controller.dto.response;

import lombok.Builder;

@Builder
public record StudentResponseSummary(Long Id,
                                     String name,
                                     String email,
                                     boolean assessment) {}
