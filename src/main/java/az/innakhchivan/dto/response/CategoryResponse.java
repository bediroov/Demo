package az.innakhchivan.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String azName;
    private String enName;
    private String ruName;
    private List<Long> entrepreneurIds;
    private List<Long> projectIds;
    private List<Long> sectorIds;
}
