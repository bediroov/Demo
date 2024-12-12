package az.innakhchivan.service;

import az.innakhchivan.dto.request.PhotoGalleryRequestDto;
import az.innakhchivan.dto.response.PhotoGalleryResponseDto;
import az.innakhchivan.entity.PhotoGallery;
import az.innakhchivan.exception.PhotoGalleryNotFoundException;
import az.innakhchivan.repository.PhotoGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoGalleryService {

    private final PhotoGalleryRepository photoGalleryRepository;


    public void addImageUrl(PhotoGalleryRequestDto photoGalleryRequestDto) {
        PhotoGallery photoGallery = new PhotoGallery();

        photoGallery.setImageUrl(photoGalleryRequestDto.getImageUrl());

        photoGalleryRepository.save(photoGallery);
    }

    public void  updateImageUrl(Long id, PhotoGalleryRequestDto photoGalleryRequestDto) {
        PhotoGallery photoGallery = photoGalleryRepository.findById(id).orElseThrow(
                ()-> new PhotoGalleryNotFoundException("PhotoGallery not found with id: " + id )
        );

        photoGallery.setImageUrl(photoGalleryRequestDto.getImageUrl());
        photoGalleryRepository.save(photoGallery);
    }


    public PhotoGalleryResponseDto getPhotoGalleryById(Long id) {
        PhotoGallery photoGalleryId = photoGalleryRepository.findById(id).orElseThrow(
                ()-> new PhotoGalleryNotFoundException("PhotoGallery not found with id: " + id )
        );
        return new PhotoGalleryResponseDto(
                photoGalleryId.getId(),
                photoGalleryId.getImageUrl()
        );
    }


    public List<PhotoGalleryResponseDto> getAllImages() {
        return photoGalleryRepository.findAll().stream()
                .map(image -> new PhotoGalleryResponseDto(
                        image.getId(),
                        image.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    public void deleteImage(Long id) {
        photoGalleryRepository.findById(id).ifPresent(photoGalleryRepository::delete);
    }
}
