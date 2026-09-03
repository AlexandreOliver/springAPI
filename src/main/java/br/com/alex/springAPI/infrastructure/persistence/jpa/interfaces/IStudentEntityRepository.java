package br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IStudentEntityRepository extends JpaRepository<StudentEntity, UUID> {

  Optional<StudentEntity> findByEmail(String email);

  @Query(
      value = """
          SELECT
              s
          FROM
              StudentEntity s
          WHERE
              LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
          """,
countQuery = """ 
          SELECT
              COUNT(s)
          FROM
              StudentEntity s
          WHERE
              LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%'))
          """
  )
  Page<StudentEntity> findAllWithQuery(Pageable pageable, String query);

  Page<StudentEntity> findAll(Pageable pageable);

  @Query("SELECT s FROM StudentEntity s LEFT JOIN FETCH s.assessment WHERE s.id = :id")
  Optional<StudentEntity> findByIdWithAssessment(UUID id);

  @Query("SELECT s FROM StudentEntity s LEFT JOIN FETCH s.assessment")
  Page<StudentEntity> findAllWithAssessment(Pageable pageable);

  @Override
  boolean existsById(UUID id);
}
