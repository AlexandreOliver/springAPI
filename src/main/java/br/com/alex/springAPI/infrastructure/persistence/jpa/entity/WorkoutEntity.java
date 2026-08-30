package br.com.alex.springAPI.infrastructure.persistence.jpa.entity;


import br.com.alex.springAPI.domain.Workout;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "workouts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WorkoutEntity {

  @Id
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "objective", nullable = false)
  private String objective;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private StudentEntity student;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
    name = "execise_workout",
      joinColumns = @JoinColumn(name = "workout_id"),
      inverseJoinColumns = @JoinColumn(name = "exercise_id")
  )
  private Set<ExerciseEntity> exercises = new HashSet<>();


  public static WorkoutEntity from(Workout workout) {
    var building = WorkoutEntity
        .builder()
        .id(workout.getId().id())
        .name(workout.getName())
        .objective(workout.getObjective());

    if (Objects.nonNull(workout.getExercises())) {
      building.exercises(workout.getExercises().stream().map(ExerciseEntity::from).collect(Collectors.toSet()));
    }

    return building.build();
  }

  public Workout toDomain() {
    return Workout.builder()
        .id(new WorkoutId(this.id))
        .name(this.name)
        .objective(this.objective)
        .exercises(this.exercises.stream().map(ExerciseEntity::toDomain).collect(Collectors.toSet()))
        .build();
  }
}
