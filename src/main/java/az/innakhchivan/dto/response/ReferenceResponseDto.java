package az.innakhchivan.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceResponseDto {
    private Long id;
    private String name;
    private String iconUrl;
    private String url;
}
