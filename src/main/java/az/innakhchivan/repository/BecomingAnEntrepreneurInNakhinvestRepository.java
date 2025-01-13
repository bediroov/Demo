package az.innakhchivan.repository;

import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BecomingAnEntrepreneurInNakhinvestRepository extends JpaRepository<BecomingAnEntrepreneurInNakhinvest, Long>,
        JpaSpecificationExecutor<BecomingAnEntrepreneurInNakhinvest> {


    @Query("SELECT e FROM BecomingAnEntrepreneurInNakhinvest e JOIN FETCH e.category c ORDER BY e.id ASC")
    List<BecomingAnEntrepreneurInNakhinvest> findAllWithCategory();

    List<BecomingAnEntrepreneurInNakhinvest> findAllByOrderByIdAsc();
}
