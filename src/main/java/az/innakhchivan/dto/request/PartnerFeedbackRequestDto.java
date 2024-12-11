package az.innakhchivan.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerFeedbackRequestDto {
    private Long id;
    private String partnerFullName;
    private String comment;
}
