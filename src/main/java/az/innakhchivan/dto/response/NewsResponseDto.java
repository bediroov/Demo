package az.innakhchivan.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate createAt;
    private String imageUrl;
    private String optional;

}

