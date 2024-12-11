package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedirectLinkResponseDto {
    private Long id;
    private String title;
    private String url;
    private String iconUrl;
    private Integer linkOrder;
    private Boolean active;
}
