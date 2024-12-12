package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhyNakhinvestResponse {
    private Long id;
    private String azTitle;
    private String azDescription;
    private String enTitle;
    private String enDescription;
    private String ruTitle;
    private String ruDescription;
    private String imageUrl;
}
