package az.innakhchivan.repository;

import az.innakhchivan.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByUniqueKey(String uniqueKey);

    @Query("SELECT r FROM Region r LEFT JOIN FETCH r.mapDataList")
    List<Region> findAllWithMapData();
}
