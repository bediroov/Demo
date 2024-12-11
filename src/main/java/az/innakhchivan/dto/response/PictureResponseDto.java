package az.innakhchivan.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureResponseDto {
    private String url;
    private String message;
}
