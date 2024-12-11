package az.innakhchivan.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HowWeHelpRequestDto {
    private String azTitle;
    private String azDescription;

    private String enTitle;
    private String enDescription;

    private String ruTitle;
    private String ruDescription;
}
