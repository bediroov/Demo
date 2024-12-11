package az.innakhchivan.repository;

import az.innakhchivan.entity.WhoAreWe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhoAreWeRepository extends JpaRepository<WhoAreWe, Long> {

}
