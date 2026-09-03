package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import java.util.Optional;

public interface IStudentRepository extends IRepositoryDomain<Student, StudentId> {

  Optional<Student> findByEmail(String email);

  Optional<Student> findByIdWithAssessment(StudentId id);

  Pagination<Student> findAllWithAssessment(PageApplication requestPage);

  boolean existsByStudentId(StudentId id);

  Pagination<Student> findAllWithQuery(PageApplication requestPage, Optional<String> query);
}
