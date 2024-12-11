package az.innakhchivan.exception;

import org.springframework.dao.DataAccessException;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
