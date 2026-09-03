package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.dtos.output.StudentOutput;
import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllStudentUseCase {

  private final IStudentRepository studentRepository;

  public List<StudentOutput> execute(boolean includeAssessment) {
    List<StudentOutput> studentOutputs;

    if (includeAssessment) {
      studentOutputs = this.studentRepository.findAllWithAssessment().stream().map(StudentOutput::of).toList();
    } else {
      studentOutputs = this.studentRepository.findAll().stream().map(StudentOutput::of).toList();
    }

    return studentOutputs;
  }
}
