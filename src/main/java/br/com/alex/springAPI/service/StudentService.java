package br.com.alex.springAPI.service;

import br.com.alex.springAPI.controller.dto.request.StudentRequestCreate;
import br.com.alex.springAPI.controller.dto.response.*;
import br.com.alex.springAPI.database.entity.PhisicalAssessment;
import br.com.alex.springAPI.database.entity.Student;
import br.com.alex.springAPI.database.entity.Workout;
import br.com.alex.springAPI.database.repository.IWorkoutRepository;
import br.com.alex.springAPI.exception.BadRequestException;
import br.com.alex.springAPI.database.repository.IStudentRepository;

import br.com.alex.springAPI.exception.NotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final IStudentRepository studentRepository;
  private final IWorkoutRepository workoutRepository;

  public void create(StudentRequestCreate dto) {

    if (this.studentRepository.findByEmail(dto.email()).isPresent()) {
      throw new BadRequestException("Corrija sua requisição e tente novamente");
    };

    this.studentRepository.save(Student
        .builder()
        .name(dto.name())
        .email(dto.email())
        .build());


  };


  public List<StudentResponseSummary> listAll() {

    List<StudentResponseSummary> alunos = this.studentRepository.findAll()
        .stream().map(student ->
            StudentResponseSummary.builder()
                .Id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .assessment(student.getAssessment() != null)
                .build())
        .toList();

    return alunos;
  };

  public AssessmentResponse getAssessment(Long id) {
    Student student = this.studentRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundExpection("Aluno não encontrado", Optional.of("Troque o id na url")));

    PhisicalAssessment assessment = student.getAssessment();
    if (assessment == null) {
      throw new NotFoundExpection("Não há avaliação fisica para esse aluno.", Optional.of("Crie uma."));
    }

    return AssessmentResponse.builder()
        .id(assessment.getId())
        .preco(assessment.getPreco())
        .altura(assessment.getAltura())
        .percentBodyFat(assessment.getPercentBodyFat())
        .build();
  }

  public List<WorkoutResponseWithoutStudent> getWorkouts(Long id) {
    Student student = this.studentRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundExpection("Aluno não encontrado", Optional.of("Troque o id na url")));

    List<Workout> workouts = this.workoutRepository.findByStudent_Id(id);
    if (workouts.isEmpty()) {
      throw new NotFoundExpection("Não há treinos para esse aluno", Optional.of("Crie."));
    }

    return workouts.stream()
        .map(workout -> WorkoutResponseWithoutStudent.builder()
            .id(workout.getId())
            .name(workout.getName())
            .objective(workout.getObjective())
            .exercises(workout.getExercises()).build()).toList();
  }

  public void delete(Long id) {

    this.studentRepository.deleteById(id);

  }
}
