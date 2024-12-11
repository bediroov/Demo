package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponseDto {
     private Long id;
     private String uniqueKey;
     private String regionName;
     private Boolean isActive;
}
