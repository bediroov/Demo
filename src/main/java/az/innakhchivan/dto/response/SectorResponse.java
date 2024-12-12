package az.innakhchivan.dto.response;

import az.innakhchivan.entity.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectorResponse {

    private Long id;
    private String azDescription;
    private String enDescription;
    private String ruDescription;
    private String imageUrl;
    private String iconUrl;
    private CategoryResponseDtoForRelation category;
}
