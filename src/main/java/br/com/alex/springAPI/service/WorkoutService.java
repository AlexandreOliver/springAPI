package br.com.alex.springAPI.service;

import br.com.alex.springAPI.controller.dto.request.WorkoutRequestCreate;
import br.com.alex.springAPI.infrastructure.persistence.entity.ExerciseEntity;
import br.com.alex.springAPI.database.entity.Student;
import br.com.alex.springAPI.database.entity.Workout;
import br.com.alex.springAPI.infrastructure.persistence.IExerciseEntityRepository;
import br.com.alex.springAPI.database.repository.IStudentRepository;
import br.com.alex.springAPI.database.repository.IWorkoutRepository;
import br.com.alex.springAPI.exception.NotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final IWorkoutRepository workoutRepository;
    private final IStudentRepository studentRepository;
    private final IExerciseEntityRepository exerciseRepository;

    public void create(WorkoutRequestCreate workoutDto) {
      Student student = this.studentRepository
          .findById(workoutDto.studentId())
          .orElseThrow(() -> new NotFoundExpection("O Aluno não existe", Optional.of("Verifique o campo studentId")));

      Set<ExerciseEntity> exercicies = new HashSet<>(this.exerciseRepository.findAllById(workoutDto.exercises()));

      System.out.println(workoutDto.objective());

      Workout workot = Workout.builder()
          .student(student)
          .name(workoutDto.name())
          .objective(workoutDto.objective())
          .exercises(exercicies)
          .build();

      this.workoutRepository.save(workot);
    }

    public void delete(Long id) {

      this.workoutRepository.deleteById(id);
    }
}
