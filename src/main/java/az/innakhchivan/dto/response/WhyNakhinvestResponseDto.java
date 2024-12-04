package az.innakhchivan.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WhyNakhinvestResponseDto {
    private Long id;
    private String title;
    private String description;
}
