package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStudentUseCase {

  private final IStudentRepository studentRepository;

  public void execute(StudentId id) {
    this.studentRepository.delete(id);
  }
}
