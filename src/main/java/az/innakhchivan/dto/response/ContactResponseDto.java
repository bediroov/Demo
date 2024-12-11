package az.innakhchivan.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String applicationContent;
    private LocalDate createdAt;
}
