package az.innakhchivan.dto.response;

import az.innakhchivan.enums.Role;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String email;
    private String username;
    private Set<Role> authorities;
}