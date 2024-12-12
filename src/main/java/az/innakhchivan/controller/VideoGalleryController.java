package az.innakhchivan.controller;

import az.innakhchivan.dto.request.VideoGalleryRequestDto;
import az.innakhchivan.dto.response.VideoGalleryResponseDto;
import az.innakhchivan.service.VideoGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video-gallery")
@RequiredArgsConstructor
public class VideoGalleryController {

    private final VideoGalleryService videoGalleryService;

    @PostMapping
    public ResponseEntity<Void> addedVideoUrl(@RequestBody VideoGalleryRequestDto videoGalleryRequestDto) {
       videoGalleryService.addVideoUrl(videoGalleryRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedVideoUrl(@PathVariable Long id,
                                                @RequestBody VideoGalleryRequestDto videoGalleryRequestDto) {
        videoGalleryService.updateVideoUrl(id,videoGalleryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGalleryResponseDto> getVideoUrlById(@PathVariable Long id) {
        VideoGalleryResponseDto response = videoGalleryService.getVideoUrlById(id);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoGalleryResponseDto>> getAllVideos() {
        List<VideoGalleryResponseDto> videos = videoGalleryService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoById(@PathVariable Long id) {
        videoGalleryService.deleteVideoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
