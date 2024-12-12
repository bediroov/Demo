package az.innakhchivan.repository;

import az.innakhchivan.entity.BecomingAnEntrepreneurInNakhinvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BecomingAnEntrepreneurInNakhinvestRepository extends JpaRepository<BecomingAnEntrepreneurInNakhinvest, Long> {


    @Query("SELECT e FROM BecomingAnEntrepreneurInNakhinvest e JOIN FETCH e.category c")
    List<BecomingAnEntrepreneurInNakhinvest> findAllWithCategory();

}
