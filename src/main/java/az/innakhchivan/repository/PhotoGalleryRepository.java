package az.innakhchivan.repository;

import az.innakhchivan.entity.PhotoGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, Long> {
    Optional<PhotoGallery> findByName(String name);
}
