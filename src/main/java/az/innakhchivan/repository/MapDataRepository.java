package az.innakhchivan.repository;

import az.innakhchivan.entity.MapData;
import az.innakhchivan.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapDataRepository extends JpaRepository<MapData,Long>, JpaSpecificationExecutor<MapData> {
    List<MapData> findByRegion(Region region);

    @Query("SELECT m FROM MapData m JOIN FETCH m.region ORDER BY m.id ASC")
    List<MapData> findAllWithRegion();

    List<MapData> findAllByOrderByIdAsc();
}
