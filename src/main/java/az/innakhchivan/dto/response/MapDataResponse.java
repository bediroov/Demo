package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapDataResponse {
    private Long id;
    private String azTitle;
    private String azDescription;
    private String enTitle;
    private String enDescription;
    private String ruTitle;
    private String ruDescription;
    private String location;
    private String area;
    private String averageSalary;
    private Integer population;
    private String iconUrl;
    private RegionResponseDtoForRelation region;
}
