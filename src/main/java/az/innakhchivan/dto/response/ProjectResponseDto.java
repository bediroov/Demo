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
    private String category;
    private String projectName;
    private String description;
    private LocalDate createdAt;

}
