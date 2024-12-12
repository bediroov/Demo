package az.innakhchivan.dto.response;

import az.innakhchivan.entity.Category;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;
    private String azTitle;
    private String azDescription;
    private String enTitle;
    private String enDescription;
    private String ruTitle;
    private String ruDescription;
    private String imageUrl;
    private CategoryResponseDtoForRelation category;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}

