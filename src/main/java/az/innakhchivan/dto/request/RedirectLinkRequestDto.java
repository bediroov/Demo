package az.innakhchivan.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedirectLinkRequestDto {
    private String title;
    private String url;
    private String iconUrl;
    private Integer linkOrder;
    private Boolean active;
}
