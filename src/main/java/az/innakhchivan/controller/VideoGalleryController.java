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
    public ResponseEntity<VideoGalleryResponseDto> addedVideoUrl(@RequestBody VideoGalleryRequestDto videoGalleryRequestDto) {
        VideoGalleryResponseDto responseDto = videoGalleryService.addVideoUrl(videoGalleryRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping
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
