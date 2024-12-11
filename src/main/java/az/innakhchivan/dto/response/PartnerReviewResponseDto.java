package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerReviewResponseDto {
    private Long id;
    private String partnerName;
    private String comment;
    private String iconUrl;

}
