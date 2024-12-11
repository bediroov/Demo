package az.innakhchivan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorRequestDto {
    private String azDescription;
    private String enDescription;
    private String ruDescription;
    private String imageUrl;
    private String iconUrl;
    private Long categoryId;
}
