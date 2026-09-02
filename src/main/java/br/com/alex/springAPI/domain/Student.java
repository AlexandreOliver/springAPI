package br.com.alex.springAPI.domain;

import br.com.alex.springAPI.domain.valueObjects.StudentId;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Student {
  private StudentId id;

  private String name;

  private String email;

  private PhysicalAssessment physicalAssessment;

  public void update(
      Optional<StudentId> id,
      Optional<String> name,
      Optional<String> email
  ) {
    id.ifPresent(value -> this.id = value);
    name.ifPresent(value -> this.name = value);
    email.ifPresent(value -> this.email = value);
  }
}
