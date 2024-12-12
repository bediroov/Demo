package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntrepreneurInNakhinvestResponse {
    private Long id;
    private String azTitle;
    private String azDescription;
    private String enTitle;
    private String enDescription;
    private String ruTitle;
    private String ruDescription;
    private String iconUrl;
    private CategoryResponseDtoForRelation category;
}

