package br.com.alex.springAPI.application.dtos.input;

import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public record PageRequestApplication ( int page, int size ) {

  public PageRequestApplication {
    Assert.isTrue(page > 0, "O parametro Page precisa ser maior do que 0");
  }

  public PageRequestApplication() {
    this(1, 10);
  }



}
