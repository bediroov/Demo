package az.innakhchivan.repository;

import az.innakhchivan.entity.PartnerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerReviewRepository  extends JpaRepository<PartnerReview, Long> {
}
