package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AboutResponseDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
}
