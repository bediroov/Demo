package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BecomingAnEntrepreneurInNakhinvestResponseDto {
    private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String iconUrl;
}
