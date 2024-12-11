package az.innakhchivan.repository;

import az.innakhchivan.entity.VideoGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoGalleryRepository extends JpaRepository<VideoGallery, Long> {
    Optional<VideoGallery> findVideoGalleryById(Long id);
}
