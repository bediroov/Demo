//package az.innakhchivan.dto.request;
//
//import az.innakhchivan.enums.Role;
//import jakarta.validation.constraints.*;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class RegisterRequestDto {
//
//    @NotBlank(message = "Email is required")
//    @Email(message = "Email should be valid")
//    private String email;
//
//    @NotBlank(message = "Username is required")
//    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
//    private String username;
//
//    @NotBlank(message = "Password is required")
//    @Size(min = 4,max = 40, message = "Password must be at least 4, max 40 characters long")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=.]).*$",
//            message = "Password must contain at least one uppercase letter, one lowercase letter, one number " +
//                    "and one special character (@#$%^&+=)"
//    )
//    private String password;
//
//
//    private Set<Role> authorities;
//}