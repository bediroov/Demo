package az.innakhchivan.repository;

import az.innakhchivan.entity.Incentive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long> {

}
