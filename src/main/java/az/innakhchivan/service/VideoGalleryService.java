package az.innakhchivan.service;

import az.innakhchivan.entity.VideoGallery;
import az.innakhchivan.repository.VideoGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoGalleryService {
    private final VideoGalleryRepository videoGalleryRepository;

    public VideoGallery saveVideo(VideoGallery video) {
        return videoGalleryRepository.save(video);
    }

    public List<VideoGallery> getAllVideos() {
        return videoGalleryRepository.findAll();
    }

    public Optional<VideoGallery> getVideoById(Long id) {
        return videoGalleryRepository.findVideoGalleryById(id);
    }
}
