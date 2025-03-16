package az.innakhchivan.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapDataRequestDto {

    private String regionUniqueKey;

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


    // Yeni əlavə edilən sahələr
    private Integer settlementCount;
    private Integer villageCount;
    private Integer cityAdminArea;
    private String distanceFromBaku;
    private String azGeneralInfo;
    private String enGeneralInfo;
    private String ruGeneralInfo;

}
