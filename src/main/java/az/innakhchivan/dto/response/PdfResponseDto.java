package az.innakhchivan.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdfResponseDto {
    private String fileUrl;
    private String message;
}
