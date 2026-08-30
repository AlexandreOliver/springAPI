package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.exception.DuplicatedEmail;
import br.com.alex.springAPI.application.input.CreateStudentInput;
import br.com.alex.springAPI.application.output.StudentOutput;
import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStudentUseCase {

  private final IStudentRepository studentRepository;

  public StudentOutput execute(CreateStudentInput studentInput) throws DuplicatedEmail {

    if (this.studentRepository.findByEmail(studentInput.email()).isPresent()) {
      throw new DuplicatedEmail(studentInput.email());
    }

    var newStudent = Student.builder()
        .id(new StudentId())
        .name(studentInput.name())
        .email(studentInput.email())
        .build();

    var studentSaved = this.studentRepository.save(newStudent);

    return StudentOutput.of(studentSaved);
  }
}
