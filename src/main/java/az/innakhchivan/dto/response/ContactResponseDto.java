package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {
    private Long id;
    private String phone;
    private String email;
    private String address;
}
