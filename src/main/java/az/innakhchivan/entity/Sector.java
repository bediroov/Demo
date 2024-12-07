package az.innakhchivan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String azCategory;
    @NotNull
    private String azDescription;

    private String enCategory;
    private String enDescription;

    private String ruCategory;
    private String ruDescription;

    public String getSectorCategory(String lang) {
        return switch (lang) {
            case "en" -> enCategory;
            case "ru" -> ruCategory;
            default -> azCategory;
        };
    }

    public String getSectorDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }


}
