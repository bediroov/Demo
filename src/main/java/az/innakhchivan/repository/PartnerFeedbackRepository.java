package az.innakhchivan.repository;

import az.innakhchivan.entity.PartnerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerFeedbackRepository extends JpaRepository<PartnerFeedback, Long> {
}
