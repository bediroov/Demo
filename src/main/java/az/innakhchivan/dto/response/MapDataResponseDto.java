package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapDataResponseDto {
    private Long id;
    private String location;
    private String area;
    private Integer population;
    private String averageSalary;

    private String title;
    private String description;

    private String iconUrl;



    // 🆕 Yeni əlavə edilən sahələr
    private Integer settlementCount;
    private Integer villageCount;
    private Integer cityAdminArea;
    private String distanceFromBaku;
    private String generalInfo;
}
