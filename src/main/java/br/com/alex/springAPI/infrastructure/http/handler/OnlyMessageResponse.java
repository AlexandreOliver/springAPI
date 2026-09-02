package br.com.alex.springAPI.infrastructure.http.handler;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo apenas uma mensagem")
public record OnlyMessageResponse(String message) {}
