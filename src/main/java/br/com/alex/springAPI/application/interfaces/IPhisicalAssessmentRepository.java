package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;
import br.com.alex.springAPI.domain.PhysicalAssessment;
import br.com.alex.springAPI.domain.valueObjects.PhisicalAssessmentId;

public interface IPhisicalAssessmentRepository extends IRepositoryDomain<PhysicalAssessment, PhisicalAssessmentId>{

  Pagination<PhysicalAssessment> findAll(PageApplication requestPage);
}
