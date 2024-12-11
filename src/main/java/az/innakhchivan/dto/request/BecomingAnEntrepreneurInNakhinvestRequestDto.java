package az.innakhchivan.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BecomingAnEntrepreneurInNakhinvestRequestDto {
    private String azTitle;
    private String azDescription;

    private String enTitle;
    private String enDescription;

    private String ruTitle;
    private String ruDescription;
    private Long categoryId;
    private String iconUrl;
}
