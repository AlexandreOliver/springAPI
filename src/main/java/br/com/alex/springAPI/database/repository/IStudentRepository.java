package br.com.alex.springAPI.database.repository;

import br.com.alex.springAPI.database.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Long> {

  Optional<Student> findById(Long id);
  Optional<Student> findByEmail(String email);
}
