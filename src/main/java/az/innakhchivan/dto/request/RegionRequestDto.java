package az.innakhchivan.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionRequestDto {
    private String uniqueKey;
    private String azName;
    private String enName;
    private String ruName;
}
