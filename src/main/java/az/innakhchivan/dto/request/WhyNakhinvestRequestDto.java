package az.innakhchivan.dto.request;

import az.innakhchivan.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhyNakhinvestRequestDto {
    private String azTitle;
    private String azDescription;

    private String enTitle;
    private String enDescription;

    private String ruTitle;
    private String ruDescription;
    private String imageUr;
}
