package az.innakhchivan.controller;

import az.innakhchivan.entity.VideoGallery;
import az.innakhchivan.service.VideoGalleryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/video")
@RequiredArgsConstructor
public class VideoGalleryController {
    private final VideoGalleryService videoGalleryService;

    @PostMapping("/upload")
    public ResponseEntity<VideoGallery> uploadVideo(@Valid @RequestBody VideoGallery video) {
        VideoGallery savedVideo = videoGalleryService.saveVideo(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVideo);
    }

    @GetMapping
    public List<VideoGallery> getAllVideos() {
        return videoGalleryService.getAllVideos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGallery> getVideoById(@PathVariable Long id) {
        return videoGalleryService.getVideoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
