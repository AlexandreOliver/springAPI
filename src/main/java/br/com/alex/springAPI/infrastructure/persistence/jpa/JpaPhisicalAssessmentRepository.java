package br.com.alex.springAPI.infrastructure.persistence.jpa;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.application.interfaces.IPhisicalAssessmentRepository;
import br.com.alex.springAPI.domain.PhysicalAssessment;
import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;

import br.com.alex.springAPI.infrastructure.persistence.jpa.entity.PhisicalAssessmentEntity;

import br.com.alex.springAPI.infrastructure.persistence.jpa.interfaces.IPhisicalAssessmentEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaPhisicalAssessmentRepository implements IPhisicalAssessmentRepository {
  private final IPhisicalAssessmentEntityRepository assessmentEntityRepository;

  @Override
  public PhysicalAssessment save(PhysicalAssessment assessment) {
    var phisicalEntity = this.assessmentEntityRepository.save(PhisicalAssessmentEntity.from(assessment));

    return phisicalEntity.toDomain();
  }

  @Override
  public Pagination<PhysicalAssessment> findAll(PageApplication requestPage) {

    Page<PhisicalAssessmentEntity> assessmentEntities = this.assessmentEntityRepository.findAll(
        PageRequest.of(requestPage.page() - 1,
            requestPage.size())
    );

    Pagination<PhysicalAssessment> pagination = Pagination.<PhysicalAssessment>builder()
        .contents(assessmentEntities.getContent().stream().map(PhisicalAssessmentEntity::toDomain).toList())
        .pageCurrent(assessmentEntities.getNumber() + 1)
        .pageSize(assessmentEntities.getSize())
        .totalElements((int) assessmentEntities.getTotalElements())
        .totalPages(assessmentEntities.getTotalPages())
        .build();

    return pagination;
  }

  @Override
  public Optional<PhysicalAssessment> findById(PhisicalAssessmentId id) {
    return Optional.ofNullable(this.assessmentEntityRepository.getReferenceById(id.uuid()).toDomain());
  }

  @Override
  public void delete(PhisicalAssessmentId id) {
    this.assessmentEntityRepository.deleteById(id.uuid());
  }
}
