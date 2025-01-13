package az.innakhchivan.exception;

import az.innakhchivan.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AboutUsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAboutUsNotFoundException(AboutUsNotFoundException ex, HttpServletRequest request) {
        log.error("NotFoundException : ", ex);
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(BecomingAnEntrepreneurInNakhinvestNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBecomingAnEntrepreneurInNakhinvestNotFoundException(
            BecomingAnEntrepreneurInNakhinvestNotFoundException ex, HttpServletRequest request) {

        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());

    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleContactNotFoundException(ContactNotFoundException ex,
                                                                            HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(PdfProcessingException.class)
    public ResponseEntity<ExceptionResponse> handlePdfProcessingException(PdfProcessingException ex,
                                                                            HttpServletRequest request) {
        return ResponseEntity.status(PROCESSING)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(PROCESSING.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(SubmitProjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSubmitProjectNotFoundException(SubmitProjectNotFoundException ex,
                                                                            HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(IncentiveNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleIncentiveNotFoundException(IncentiveNotFoundException ex,
                                                                              HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNewsNotFoundException(NewsNotFoundException ex,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProjectNotFoundException(ProjectNotFoundException ex,
                                                                            HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleQuestionNotFoundException(QuestionNotFoundException ex,
                                                                             HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(SectorNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSectorNotFoundException(SectorNotFoundException ex,
                                                                           HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                                              HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(VideoGalleryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleVideoGalleryNotFoundException(VideoGalleryNotFoundException ex,
                                                                                 HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRegionNotFoundException(RegionNotFoundException ex,
                                                                           HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(WhyNakhinvestNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleWhyNakhinvestNotFoundException(WhyNakhinvestNotFoundException ex,
                                                                                  HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<String> handleDatabaseException(DatabaseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid data: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }


    // Handle ImageProcessingException
    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleImageProcessingException(ImageProcessingException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    // Handle ImageNotFoundException
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleImageNotFoundException(ImageNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<String> handleCustomValidationException(CustomValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

//    // Default handler for all exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ExceptionResponse.builder()
//                        .message("An unexpected error occurred: " + ex.getMessage())
//                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .path(request.getRequestURI())
//                        .timeStamp(LocalDateTime.now())
//                        .build());
//    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(DuplicateUniqueKeyException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateUniqueKeyException(DuplicateUniqueKeyException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(LazyInitializationException.class)
    public ResponseEntity<String> handleLazyInitializationException(LazyInitializationException ex) {
        return new ResponseEntity<>("Failed to fetch related data: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(PartnerReviewNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePartnerReviewNotFoundException(PartnerReviewNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(ReferenceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleReferenceNotFoundException(ReferenceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(PhotoGalleryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePhotoGalleryNotFoundException(PhotoGalleryNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    //AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(UNAUTHORIZED.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    //UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(NOT_FOUND.value())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    public ResponseEntity<Map<String, String>> handleExpiredJwtException(io.jsonwebtoken.ExpiredJwtException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "TokenExpired");
        response.put("message", "JWT token has expired");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
