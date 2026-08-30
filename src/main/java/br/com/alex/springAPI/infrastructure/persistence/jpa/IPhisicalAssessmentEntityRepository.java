package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.PhisicalAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPhisicalAssessmentEntityRepository extends JpaRepository<PhisicalAssessmentEntity, UUID> {
}
