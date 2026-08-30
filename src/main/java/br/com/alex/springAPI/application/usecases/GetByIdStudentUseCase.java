package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.exception.NotFoundError;
import br.com.alex.springAPI.application.output.StudentOutput;
import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetByIdStudentUseCase {
  private final IStudentRepository studentRepository;

  public StudentOutput execute(StudentId id, boolean includeAssessment) throws NotFoundError {
    Student studentInDb;

    if (includeAssessment) {
      studentInDb = this.studentRepository.findByIdWithAssessment(id).orElseThrow(() -> new NotFoundError("Não há aluno com esse Id"));
    } else {
      studentInDb = this.studentRepository.findById(id).orElseThrow(() -> new NotFoundError("Não há aluno com esse Id"));
    }

    return StudentOutput.of(studentInDb);
  }
}
