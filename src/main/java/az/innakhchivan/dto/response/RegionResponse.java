package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegionResponse {
    private Long id;
    private String uniqueKey;
    private String name;
    private Boolean isActive;
}
