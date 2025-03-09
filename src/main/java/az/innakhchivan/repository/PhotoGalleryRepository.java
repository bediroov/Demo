package az.innakhchivan.repository;

import az.innakhchivan.entity.PhotoGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, Long> {
    List<PhotoGallery> findAllByOrderByIdAsc();
}
