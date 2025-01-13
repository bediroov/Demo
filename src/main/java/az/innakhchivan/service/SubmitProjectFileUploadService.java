package az.innakhchivan.service;

import az.innakhchivan.dto.response.PdfResponseDto;
import az.innakhchivan.exception.PdfProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmitProjectFileUploadService {

    @Value("${base.url.pdf}")
    private String baseUrl;

    @Value("${pdf.upload.dir}")
    private String uploadDir;

    @Value("${pdf.upload.max-size}")
    private long maxFileSize;

    public PdfResponseDto uploadPdf(MultipartFile pdfFile) throws PdfProcessingException {
        try {
            // Validate file
            if (pdfFile.getOriginalFilename() == null || pdfFile.isEmpty()) {
                throw new IllegalArgumentException("File is missing or invalid");
            }

            if (!pdfFile.getOriginalFilename().endsWith(".pdf")) {
                throw new IllegalArgumentException("Only PDF files are allowed");
            }

            // Validate file size
            if (pdfFile.getSize() > maxFileSize) {
                throw new IllegalArgumentException("File size exceeds the maximum allowed limit of " + (maxFileSize / (1024 * 1024)) + " MB");
            }

            // Ensure the upload directory exists
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                boolean created = uploadFolder.mkdirs();
                if (!created) {
                    throw new IOException("Failed to create upload directory");
                }
            }

            // Generate a unique file name
            String originalFilename = pdfFile.getOriginalFilename();
            String uniqueFileName = System.currentTimeMillis() + "-" + UUID.randomUUID() + ".pdf";

            // Save the file to the upload directory
            File destinationFile = new File(uploadFolder, uniqueFileName);
            pdfFile.transferTo(destinationFile);

            // Construct the URL for the uploaded PDF
            String pdfUrl = baseUrl + "/pdf/" + uniqueFileName;

            return PdfResponseDto.builder()
                    .fileUrl(pdfUrl)
                    .message("PDF successfully uploaded")
                    .build();

        } catch (IOException e) {
            throw new PdfProcessingException("Error saving the PDF file: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid file input: " + e.getMessage(), e);
        }
    }

        public ResponseEntity<Resource> downloadPdfByUrl(String fileUrl) throws PdfProcessingException {
            try {
                String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

                Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists() && resource.isReadable()) {
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                            .body(resource);
                } else {
                    throw new IllegalArgumentException("File not found or not readable: " + fileUrl);
                }
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid file URL: " + fileUrl, e);
            } catch (Exception e) {
                throw new PdfProcessingException("Error retrieving the file: " + e.getMessage(), (IOException) e);
            }
        }

}
