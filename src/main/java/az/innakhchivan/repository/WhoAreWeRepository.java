package az.innakhchivan.repository;

import az.innakhchivan.entity.WhoAreWe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhoAreWeRepository extends JpaRepository<WhoAreWe, Long>, JpaSpecificationExecutor<WhoAreWe> {
    List<WhoAreWe> findAllByOrderByIdAsc();

}
