package az.innakhchivan.repository;

import az.innakhchivan.entity.WhyNakhinvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhyNakhinvestRepository extends JpaRepository<WhyNakhinvest, Long> {
}
