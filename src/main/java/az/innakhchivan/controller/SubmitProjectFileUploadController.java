package az.innakhchivan.controller;

import az.innakhchivan.dto.response.PdfResponseDto;
import az.innakhchivan.exception.PdfProcessingException;
import az.innakhchivan.service.SubmitProjectFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/pdf")
@RequiredArgsConstructor
public class SubmitProjectFileUploadController {

    private final SubmitProjectFileUploadService submitProjectFileUploadService;

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PdfResponseDto> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            PdfResponseDto response = submitProjectFileUploadService.uploadPdf(file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PdfProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(PdfResponseDto.builder()
                            .message("PDF processing failed: " + e.getMessage())
                            .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(PdfResponseDto.builder()
                            .message("Invalid input: " + e.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(PdfResponseDto.builder()
                            .message("Unexpected error occurred: " + e.getMessage())
                            .build());
        }

    }

//    @GetMapping("/download")
//    public ResponseEntity<Resource> downloadPdfByUrl(@RequestParam("url") String fileUrl) throws PdfProcessingException {
//        return submitProjectFileUploadService.downloadPdfByUrl(fileUrl);
//    }

}
