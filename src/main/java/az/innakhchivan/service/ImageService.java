package az.innakhchivan.service;

import az.innakhchivan.dto.response.ImageResponseDto;
import az.innakhchivan.dto.response.PhotoGalleryResponseDto;
import az.innakhchivan.exception.ImageProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${image.upload.dir}")
    private String uploadDir;

    public ImageResponseDto uploadImage(MultipartFile imageFile) {
        try {
            // Validate file
            if (imageFile.getOriginalFilename() == null || imageFile.isEmpty()) {
                throw new IllegalArgumentException("File is missing or invalid");
            }

            // Ensure the upload directory exists
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                boolean created = uploadFolder.mkdirs();
                if (!created) {
                    throw new IOException("Failed to create upload directory");
                }
            }

            // Generate a unique file name
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = System.currentTimeMillis() + "-" + UUID.randomUUID() + fileExtension;

            // Save the file to the upload directory
            File destinationFile = new File(uploadFolder, uniqueFileName);
            imageFile.transferTo(destinationFile);

            // Construct the URL for the uploaded image
            String imageUrl = baseUrl + "/images/" + uniqueFileName;

            return ImageResponseDto.builder()
                    .imageUrl(imageUrl)
                    .message("Image successfully uploaded")
                    .build();

        } catch (IOException e) {
            throw new ImageProcessingException("Error saving the image file: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid file input: " + e.getMessage(), e);
        }
    }

}