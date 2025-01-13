package az.innakhchivan.repository;

import az.innakhchivan.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query("SELECT p FROM Project p JOIN FETCH p.category c ORDER BY p.id ASC")
    List<Project> findAllWithCategory();

    List<Project> findAllByOrderByIdAsc();


}
