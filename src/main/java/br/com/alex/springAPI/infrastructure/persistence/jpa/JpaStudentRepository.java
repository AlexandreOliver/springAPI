package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.domain.Student;
import br.com.alex.springAPI.domain.interfaces.IStudentRepository;
import br.com.alex.springAPI.domain.valueObjects.StudentId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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
  public List<Student> findAll() {
    return this.studentEntityRepository.findAll().stream().map(StudentEntity::toDomain).toList();
  }

  @Override
  public List<Student> findAllWithAssessment() {
    return this.studentEntityRepository.findAllWithAssessment().stream().map(StudentEntity::toDomain).toList();
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
