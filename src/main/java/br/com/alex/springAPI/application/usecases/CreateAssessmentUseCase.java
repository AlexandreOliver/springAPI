package br.com.alex.springAPI.application.usecases;


import br.com.alex.springAPI.domain.PhysicalAssessment;
import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.dtos.input.CreatePhisicalAssessmentInput;
import br.com.alex.springAPI.application.exception.DuplicatedAssessmentError;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateAssessmentUseCase {
  private final IStudentRepository studentRepository;

  public void execute(CreatePhisicalAssessmentInput assessmentInput, StudentId id) throws NotFoundError, DuplicatedAssessmentError {

    Student student = this.studentRepository
        .findByIdWithAssessment(id)
        .orElseThrow(() -> new NotFoundError("Aluno não encontrado"));


    if (Objects.nonNull(student.getPhysicalAssessment())) {
      throw new DuplicatedAssessmentError("Já existe um exame fisico para esse aluno: " + id.uuid(), assessmentInput);
    }

    PhysicalAssessment assessment = PhysicalAssessment.builder()
        .id(new PhisicalAssessmentId())
        .preco(assessmentInput.preco())
        .altura(assessmentInput.altura())
        .percentBodyFat(assessmentInput.percentBodyFat())
        .build();

    student.setPhysicalAssessment(assessment);

    this.studentRepository.save(student);

  }

}
