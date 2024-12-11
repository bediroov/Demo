package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGalleryResponseDto {
    private Long id;
    private String imageUrl;
}
