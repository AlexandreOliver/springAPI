package br.com.alex.springAPI.infrastructure.persistence.jpa.entity;

import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentEntity {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "assessment_id", nullable = true)
  private PhisicalAssessmentEntity assessment;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<WorkoutEntity> workouts = new HashSet<>();

  public static StudentEntity from(Student student) {

    var studentEntity = StudentEntity
        .builder()
        .id(student.getId().uuid())
        .name(student.getName())
        .email(student.getEmail());

    if (student.getPhisicalAssessment() != null) {
      studentEntity.assessment(PhisicalAssessmentEntity.from(student.getPhisicalAssessment()));
    }

    return studentEntity.build();
  }

  public Student toDomain() {

    var StudentDomain = Student.builder()
        .id(new StudentId(this.id))
        .name(this.name)
        .email(this.email);

    if (this.assessment != null) {
      StudentDomain.phisicalAssessment(this.assessment.toDomain());
    }

    return StudentDomain.build();
  }
}


