package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoGalleryResponseDto {
    private Long id;
    private String videoUrl;
}
