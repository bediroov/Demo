package az.innakhchivan.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String applicationContent;
}
