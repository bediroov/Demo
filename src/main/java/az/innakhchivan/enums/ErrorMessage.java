package az.innakhchivan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    USER_NOT_FOUND("User not found"),
    USER_NOT_FOUND_BY_ID("User not found with id: %d"),
    WRONG_PASSWORD_OR_USERNAME("The username or password is incorrect"),
    ACCESS_DENIED("You are not authorized to access this resource"),
    UNEXPECTED_EXCEPTION("Unknown internal server error"),
    USERNAME_NOT_FOUND("Username not found with name: %s");

    private final String message;
    public String format(Object... args) {
        return String.format(message, args);
    }
}