package az.innakhchivan.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {
    private String phone;
    private String email;
    private String address;
}
