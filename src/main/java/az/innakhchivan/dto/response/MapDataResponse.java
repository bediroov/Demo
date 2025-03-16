package az.innakhchivan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // 🆕 NULL gələn sahələri də serialize et

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



//    private String generalInfo;

    private Integer settlementCount;
    private Integer villageCount;
    private Integer cityAdminArea;
    private String distanceFromBaku;
//
    private String azGeneralInfo;
    private String enGeneralInfo;
    private String ruGeneralInfo;



}
