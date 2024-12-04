package az.innakhchivan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerFeedbackResponseDto {
    private Long id;
    private String partnerFullName;
    private String comment;
}
