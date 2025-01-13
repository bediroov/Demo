package az.innakhchivan.repository;

import az.innakhchivan.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.entrepreneurs e LEFT JOIN FETCH c.projects p LEFT JOIN FETCH c.sector s ORDER BY c.id ASC")
    List<Category> findAllWithRelations();

    List<Category> findAllByOrderByIdAsc();

}
