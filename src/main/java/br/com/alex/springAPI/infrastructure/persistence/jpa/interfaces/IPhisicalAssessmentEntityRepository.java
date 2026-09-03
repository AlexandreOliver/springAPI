package br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.PhisicalAssessmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPhisicalAssessmentEntityRepository extends JpaRepository<PhisicalAssessmentEntity, UUID> {

  Page<PhisicalAssessmentEntity> findAll(Pageable pageable);
}
