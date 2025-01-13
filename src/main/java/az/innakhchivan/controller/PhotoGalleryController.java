package az.innakhchivan.controller;

import az.innakhchivan.dto.request.PhotoGalleryRequestDto;
import az.innakhchivan.dto.response.ImageResponseDto;
import az.innakhchivan.dto.response.PhotoGalleryResponseDto;
import az.innakhchivan.service.PhotoGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class PhotoGalleryController {
    private final PhotoGalleryService photoGalleryService;

    @PostMapping
    public ResponseEntity<Void>  addImageUrl(@RequestBody PhotoGalleryRequestDto photoGalleryRequestDto) {
       photoGalleryService.addImageUrl(photoGalleryRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void>  updatedImageUrl(@PathVariable Long id,  @RequestBody PhotoGalleryRequestDto photoGalleryRequestDto) {
        photoGalleryService.updateImageUrl(id, photoGalleryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoGalleryResponseDto> getImageUrlById(@PathVariable Long id) {
        PhotoGalleryResponseDto responseDto = photoGalleryService.getPhotoGalleryById(id);
        return new ResponseEntity<>(responseDto ,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhotoGalleryResponseDto>> getAllImages() {
        List<PhotoGalleryResponseDto> responseDto = photoGalleryService.getAllImages();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void>  deleteImage(Long id) {
        photoGalleryService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
