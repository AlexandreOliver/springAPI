package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IStudentEntityRepository extends JpaRepository<StudentEntity, UUID> {

  Optional<StudentEntity> findByEmail(String email);

  @Query("SELECT s FROM StudentEntity s LEFT JOIN FETCH s.assessment WHERE s.id = :id")
  Optional<StudentEntity> findByIdWithAssessment(UUID id);

  @Query("SELECT s FROM StudentEntity s LEFT JOIN FETCH s.assessment")
  List<StudentEntity> findAllWithAssessment();
}
