package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncentiveResponseDto {
    private Long id;
    private String title;
    private String description;
}
