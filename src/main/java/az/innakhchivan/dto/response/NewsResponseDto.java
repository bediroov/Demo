package az.innakhchivan.dto.response;

import az.innakhchivan.entity.PhotoGallery;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class NewsResponseDto {

    private Long id;
    private String title;
    private String description;
    private String author;
    private LocalDate created;

    private String imageName;
    private String imageType;
    private byte[] imageData;
}

