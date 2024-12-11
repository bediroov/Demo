package az.innakhchivan.dto.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String categoryName;
    private LocalDate createdAt;

}
