package az.innakhchivan.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponseDto {
     private Long id;
     private String uniqueKey;
     private String azName;
     private String enName;
     private String ruName;
     private Boolean isActive;
     private List<MapDataResponseDtoForRegion> mapDataList;
}
