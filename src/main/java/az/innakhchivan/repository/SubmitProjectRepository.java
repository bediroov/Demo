package az.innakhchivan.repository;

import az.innakhchivan.entity.SubmitProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitProjectRepository extends JpaRepository<SubmitProject, Long> {

    List<SubmitProject> findAllByOrderByIdAsc();
}
