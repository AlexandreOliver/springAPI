package br.com.alex.springAPI.service;


import br.com.alex.springAPI.controller.dto.request.AssessmentRequestCreate;
import br.com.alex.springAPI.database.model.PhisicalAssessment;
import br.com.alex.springAPI.database.model.Student;
import br.com.alex.springAPI.database.repository.IPhisicalAssessmentRepository;
import br.com.alex.springAPI.database.repository.IStudentRepository;
import br.com.alex.springAPI.exception.DuplicatedAssessmentError;
import br.com.alex.springAPI.exception.NotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentService {

  private final IPhisicalAssessmentRepository assessmentRepository;
  private final IStudentRepository studentRepository;

  public void save(AssessmentRequestCreate assessment) throws NotFoundExpection, DuplicatedAssessmentError {

    Student student = this.studentRepository
        .findById(assessment.alunoId())
        .orElseThrow(() -> new NotFoundExpection("Aluno não encontrado", Optional.of("Verifique o Id e tente novamente")));


    if (Optional.ofNullable(student.getAssessment()).isPresent()) {
      throw new DuplicatedAssessmentError("Já existe um exame fisico para esse aluno");
    };

    student.setAssessment(PhisicalAssessment.builder()
        .preco(assessment.preco())
        .altura(assessment.altura())
        .percentBodyFat(assessment.percentBodyFat())
        .build());

    this.studentRepository.save(student);
  }
}
