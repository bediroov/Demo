package az.innakhchivan.exception;

import az.innakhchivan.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AboutUsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handle(AboutUsNotFoundException ex, HttpServletRequest request) {
        log.error("NotFoundException : ", ex);
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


}
