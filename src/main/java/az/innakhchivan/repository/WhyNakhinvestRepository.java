package az.innakhchivan.repository;

import az.innakhchivan.entity.WhyNakhinvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhyNakhinvestRepository extends JpaRepository<WhyNakhinvest, Long>, JpaSpecificationExecutor<WhyNakhinvest> {
    List<WhyNakhinvest> findAllByOrderByIdAsc();
}
