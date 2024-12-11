package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhoAreWeResponseDto {
    private Long id;
    private String title;
    private String description;
}
