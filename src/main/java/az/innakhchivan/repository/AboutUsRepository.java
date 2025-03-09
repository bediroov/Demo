package az.innakhchivan.repository;

import az.innakhchivan.entity.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsRepository extends JpaRepository<AboutUs, Long>, JpaSpecificationExecutor<AboutUs> {
    List<AboutUs> findAllByOrderByIdAsc();
}

