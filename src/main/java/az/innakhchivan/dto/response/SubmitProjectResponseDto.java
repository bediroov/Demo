package az.innakhchivan.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitProjectResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String message;
    private String fileUrl;
    private LocalDate submitDate;
}
