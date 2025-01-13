package az.innakhchivan.repository;

import az.innakhchivan.entity.PartnerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerReviewRepository  extends JpaRepository<PartnerReview, Long>, JpaSpecificationExecutor<PartnerReview> {
    List<PartnerReview> findAllByOrderByIdAsc();
}
