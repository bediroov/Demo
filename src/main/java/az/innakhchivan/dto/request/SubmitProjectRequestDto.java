package az.innakhchivan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitProjectRequestDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String message;
    private String fileUrl;
}
