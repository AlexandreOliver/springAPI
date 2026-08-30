package br.com.alex.springAPI.infrastructure.persistence.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "workouts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Workout {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "objective", nullable = false)
  private String objective;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToMany
  @JoinTable(
    name = "execise_workout",
      joinColumns = @JoinColumn(name = "workout_id"),
      inverseJoinColumns = @JoinColumn(name = "exercise_id")
  )
  private Set<ExerciseEntity> exercises = new HashSet<>();


}
