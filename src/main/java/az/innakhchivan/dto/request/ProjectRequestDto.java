package az.innakhchivan.dto.request;

import az.innakhchivan.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestDto {
    private String azTitle;
    private String azDescription;
    private String enTitle;
    private String enDescription;
    private String ruTitle;
    private String ruDescription;
    private String imageUrl;
    private Long categoryId;
}
