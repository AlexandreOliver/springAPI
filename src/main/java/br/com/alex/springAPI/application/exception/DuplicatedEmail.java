package br.com.alex.springAPI.application.exception;

public class DuplicatedEmail extends ApplicationError {
  private String emailReceived;

  public DuplicatedEmail(String emailReceived) {
    super("DuplicatedEmail", "O Email ja existe no banco");

    this.emailReceived = emailReceived;

  }
}

