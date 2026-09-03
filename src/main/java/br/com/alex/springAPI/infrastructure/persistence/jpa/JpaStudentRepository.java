package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.application.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;

import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IStudentEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaStudentRepository implements IStudentRepository {
  private final IStudentEntityRepository studentEntityRepository;

  @Override
  public Student save(Student student) {

    var studentEntity = StudentEntity.from(student);

    var studentSaved = this.studentEntityRepository.save(studentEntity);

    System.out.println(studentSaved.toString());

    return studentSaved.toDomain();
  }

  @Override
  public Pagination<Student> findAll(PageApplication requestPage) {

    Page<StudentEntity> studentEntities = this.studentEntityRepository.findAll(
        PageRequest.of(requestPage.page() - 1, requestPage.size())
    );

    Pagination<Student> pagination = Pagination.<Student>builder()
        .contents(studentEntities.getContent().stream().map(StudentEntity::toDomain).toList())
        .totalElements((int) studentEntities.getTotalElements())
        .totalPages(studentEntities.getTotalPages())
        .pageCurrent(studentEntities.getNumber() + 1)
        .pageSize(studentEntities.getSize())
        .build();


    return pagination;
  }

  @Override
  public Pagination<Student> findAllWithQuery(PageApplication requestPage, Optional<String> query) {

    Page<StudentEntity> studentEntities = this.studentEntityRepository.findAllWithQuery(
        PageRequest.of(requestPage.page() - 1, requestPage.size()),
        query.orElse("")
    );

    Pagination<Student> pagination = Pagination.<Student>builder()
            .contents(studentEntities.getContent().stream().map(StudentEntity::toDomain).toList())
            .totalElements((int) studentEntities.getTotalElements())
            .totalPages(studentEntities.getTotalPages())
            .pageCurrent(studentEntities.getNumber() + 1)
            .pageSize(studentEntities.getSize())
            .build();


    return pagination;
  }

  @Override
  public Pagination<Student> findAllWithAssessment(PageApplication requestPage) {
    Page<StudentEntity> studentEntities = this.studentEntityRepository.findAllWithAssessment(
        PageRequest.of(requestPage.page() - 1, requestPage.size())
    );

    return Pagination.<Student>builder()
            .contents(studentEntities.getContent().stream().map(StudentEntity::toDomain).toList())
            .totalElements((int) studentEntities.getTotalElements())
            .totalPages(studentEntities.getTotalPages())
            .pageCurrent(studentEntities.getNumber() + 1)
            .pageSize(studentEntities.getSize())
            .build();
  }

  @Override
  public Optional<Student> findById(StudentId id) {
    return this.studentEntityRepository.findById(id.uuid()).map(StudentEntity::toDomain);
  }

  @Override
  public Optional<Student> findByIdWithAssessment(StudentId id) {
    return this.studentEntityRepository.findByIdWithAssessment(id.uuid()).map(StudentEntity::toDomain);
  }

  @Override
  public void delete(StudentId id) {
    this.studentEntityRepository.deleteById(id.uuid());
  }

  @Override
  public Optional<Student> findByEmail(String email) {

    return this.studentEntityRepository.findByEmail(email).map(StudentEntity::toDomain);
  }

  @Override
  public boolean existsByStudentId(StudentId id) {
    return this.studentEntityRepository.existsById(id.uuid());
  }

}
