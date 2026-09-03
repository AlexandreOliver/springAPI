package br.com.alex.springAPI.application.dtos.input;

import br.com.alex.springAPI.application.dtos.PageApplication;
import lombok.Builder;

import java.util.Optional;

@Builder
public record FindWithQueryCommand(
    PageApplication request_page,
    Optional<String> query
) {

  public FindWithQueryCommand(int page, int size, String query) {
    this(new PageApplication(page, size), Optional.ofNullable(query));
  }
}
