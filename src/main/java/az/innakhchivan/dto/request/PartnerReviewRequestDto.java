package az.innakhchivan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerReviewRequestDto {
    private String azPartnerName;
    private String azComment;

    private String enPartnerName;
    private String enComment;

    private String ruPartnerName;
    private String ruComment;

    private String iconUrl;
}
