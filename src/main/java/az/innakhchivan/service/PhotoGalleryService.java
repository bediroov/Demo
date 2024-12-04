package az.innakhchivan.service;

import az.innakhchivan.entity.PhotoGallery;
import az.innakhchivan.helper.ImageUtils;
import az.innakhchivan.repository.PhotoGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class PhotoGalleryService {
    private final PhotoGalleryRepository photoGalleryRepository;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = PhotoGallery.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        photoGalleryRepository.save(imageToSave);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

//    public byte[] downloadImage(String imageName) {
//        Optional<PhotoGallery> dbImage = photoGalleryRepository.findByName(imageName);
//
//
//        return dbImage.map(image -> {
//            try {
//                return ImageUtils.decompressImage(image.getImageData());
//            } catch (DataFormatException | IOException exception) {
//                throw new ContextedRuntimeException("Error downloading an image", exception)
//                        .addContextValue("Image ID", image.getId())
//                        .addContextValue("Image name", imageName);
//            }
//        }).orElse(null);
//    }

    public byte[] downloadImage(Long imageId) {
        Optional<PhotoGallery> dbImage = photoGalleryRepository.findById(imageId);

        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID", image.getId())
                        .addContextValue("Image ID", imageId);
            }
        }).orElse(null);
    }

}
