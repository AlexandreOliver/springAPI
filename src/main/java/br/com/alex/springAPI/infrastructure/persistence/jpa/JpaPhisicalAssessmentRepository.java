package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.domain.interfaces.IPhisicalAssessmentRepository;
import br.com.alex.springAPI.domain.PhisicalAssessment;
import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.PhisicalAssessmentEntity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaPhisicalAssessmentRepository implements IPhisicalAssessmentRepository {
  private final IPhisicalAssessmentEntityRepository assessmentEntityRepository;

  @Override
  public PhisicalAssessment save(PhisicalAssessment assessment) {
    var phisicalEntity = this.assessmentEntityRepository.save(PhisicalAssessmentEntity.from(assessment));

    return phisicalEntity.toDomain();
  }

  @Override
  public List<PhisicalAssessment> findAll() {

    return this.assessmentEntityRepository.findAll().stream().map(PhisicalAssessmentEntity::toDomain).toList();
  }

  @Override
  public Optional<PhisicalAssessment> findById(PhisicalAssessmentId id) {
    return Optional.ofNullable(this.assessmentEntityRepository.getReferenceById(id.uuid()).toDomain());
  }

  @Override
  public void delete(PhisicalAssessmentId id) {
    this.assessmentEntityRepository.deleteById(id.uuid());
  }
}
