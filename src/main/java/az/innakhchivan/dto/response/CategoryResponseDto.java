package az.innakhchivan.dto.response;

import lombok.*;
import org.hibernate.validator.constraints.Mod10Check;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
}
