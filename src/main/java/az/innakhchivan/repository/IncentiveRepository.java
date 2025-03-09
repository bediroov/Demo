package az.innakhchivan.repository;

import az.innakhchivan.entity.Incentive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long>, JpaSpecificationExecutor<Incentive> {
    List<Incentive> findAllByOrderByIdAsc();

}
