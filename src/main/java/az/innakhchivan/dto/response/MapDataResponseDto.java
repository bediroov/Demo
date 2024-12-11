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
}
