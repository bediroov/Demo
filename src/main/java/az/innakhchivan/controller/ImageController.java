package az.innakhchivan.controller;

import az.innakhchivan.dto.response.ImageResponseDto;
import az.innakhchivan.dto.response.PhotoGalleryResponseDto;
import az.innakhchivan.exception.ImageProcessingException;
import az.innakhchivan.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService photoGalleryService;

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Şəkili yükləyir və cavab olaraq URL qaytarır
            ImageResponseDto response = photoGalleryService.uploadImage(file);
            return ResponseEntity.ok(response);
        } catch (ImageProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ImageResponseDto.builder()
                            .message("Image processing failed: " + e.getMessage())
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ImageResponseDto.builder()
                            .message("Invalid input: " + e.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ImageResponseDto.builder()
                            .message("Unexpected error occurred: " + e.getMessage())
                            .build());
        }
    }
}