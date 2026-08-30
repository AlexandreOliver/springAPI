package br.com.alex.springAPI.application.output;

import br.com.alex.springAPI.domain.Student;
import lombok.Builder;

import java.util.UUID;

@Builder
public record StudentOutput(
    UUID Id,
    String name,
    String email,
    AssessmentOutput assessment
) {
  public static StudentOutput of(Student student) {
    var building = StudentOutput.builder()
        .Id(student.getId().uuid())
        .name(student.getName())
        .email(student.getEmail());

    if (student.getPhisicalAssessment() != null) {
      building.assessment(AssessmentOutput.of(student.getPhisicalAssessment()));
    }

    return building.build();
  }
}

