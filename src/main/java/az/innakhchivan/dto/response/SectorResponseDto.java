package az.innakhchivan.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectorResponseDto {
    private Long id;
    private String category;
    private String description;

}
