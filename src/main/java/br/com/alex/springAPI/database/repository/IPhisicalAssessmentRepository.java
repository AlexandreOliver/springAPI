package br.com.alex.springAPI.database.repository;

import br.com.alex.springAPI.database.entity.PhisicalAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhisicalAssessmentRepository extends JpaRepository<PhisicalAssessment, Long> {
}
