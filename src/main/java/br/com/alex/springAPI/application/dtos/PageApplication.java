package br.com.alex.springAPI.application.dtos;

import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public record PageApplication(int page, int size ) {

  public PageApplication {
    Assert.isTrue(page > 0, "O parametro Page precisa ser maior do que 0");
  }

  public PageApplication() {
    this(1, 10);
  }

}
