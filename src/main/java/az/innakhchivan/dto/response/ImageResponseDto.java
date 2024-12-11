package az.innakhchivan.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageResponseDto {
    private String message;
    private String imageUrl;

}
