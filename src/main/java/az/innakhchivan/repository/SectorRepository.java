package az.innakhchivan.repository;

import az.innakhchivan.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT s FROM Sector s LEFT JOIN FETCH s.category c")
    List<Sector> findAllWithCategory();

}
