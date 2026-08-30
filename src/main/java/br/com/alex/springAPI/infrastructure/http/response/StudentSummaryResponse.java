package br.com.alex.springAPI.infrastructure.http.response;

import br.com.alex.springAPI.application.output.StudentOutput;
import br.com.alex.springAPI.domain.Student;
import lombok.Builder;

import java.util.UUID;

@Builder
public record StudentSummaryResponse(
    UUID id,
    String name,
    String email
)
{
  public static StudentSummaryResponse of(StudentOutput studentOutput) {

    var building = StudentSummaryResponse.builder()
        .id(studentOutput.Id())
        .name(studentOutput.name())
        .email(studentOutput.email());

    return building.build();
  }
}
