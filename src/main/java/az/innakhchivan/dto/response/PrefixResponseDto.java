package az.innakhchivan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrefixResponseDto {
    private String prefix;
    private String countryName;
}
