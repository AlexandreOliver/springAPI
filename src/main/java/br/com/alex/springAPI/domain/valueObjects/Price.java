package br.com.alex.springAPI.domain.valueObjects;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public record Price(BigDecimal amount) {

  public Price {
    Objects.requireNonNull(amount);
  }

  private static final Locale locale = Locale.forLanguageTag("pt-BR");
  private static final BigDecimal oneRealInCents = new BigDecimal("100");

  public static Price of(String amount) {
    DecimalFormat formatador = (DecimalFormat) NumberFormat.getInstance(locale);
    formatador.setParseBigDecimal(true);

    try {
      BigDecimal amountReceived = (BigDecimal) formatador.parse(amount);
      return new Price(amountReceived);
    } catch (ParseException ex) {
      throw new RuntimeException("O argumento amount precisa estar no formato de um numero decimal: 1.2 ou 1,2");
    }
  }

  public static Price ofCents(Long amount) {
    return new Price(new BigDecimal(amount).divide(oneRealInCents, 2, RoundingMode.HALF_UP));
  }

  public int inCents() {
    return this.amount.multiply(oneRealInCents).intValue();
  }

}