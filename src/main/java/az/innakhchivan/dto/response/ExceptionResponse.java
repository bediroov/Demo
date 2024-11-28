package az.innakhchivan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class ExceptionResponse {
    private Integer statusCode;
    private String message;
    private Map<String, String> errors;
    private String path;
    private LocalDateTime timeStamp;
}
