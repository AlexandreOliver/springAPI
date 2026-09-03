package br.com.alex.springAPI.application.input;

import lombok.Builder;

import java.util.Optional;

@Builder
public record FindAllCommand(
    PageRequestApplication request_page,
    Optional<String> query
) {

  public FindAllCommand(int page, int size, String query) {
    this(new PageRequestApplication(page, size), Optional.ofNullable(query));
  }
}
