package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.application.dtos.output.StudentOutput;
import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllStudentUseCase {

  private final IStudentRepository studentRepository;

  public Pagination<StudentOutput> execute(PageApplication page, boolean includeAssessment) {
    Pagination<StudentOutput> studentOutputs;

    if (includeAssessment) {
      studentOutputs = this.studentRepository.findAllWithAssessment(page).map(StudentOutput::of);
    } else {
      studentOutputs = this.studentRepository.findAll(page).map(StudentOutput::of);
    }

    return studentOutputs;
  }
}
