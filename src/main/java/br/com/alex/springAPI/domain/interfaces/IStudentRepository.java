package br.com.alex.springAPI.domain.interfaces;

import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends IRepositoryDomain<Student, StudentId> {

  Optional<Student> findByEmail(String email);

  Optional<Student> findByIdWithAssessment(StudentId id);

  List<Student> findAllWithAssessment();
}
