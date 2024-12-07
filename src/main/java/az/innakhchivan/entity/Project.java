package az.innakhchivan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String azCategory;
    @NotNull
    private String azProjectName;
    @NotNull
    private String azDescription;

    private String enCategory;
    private String enProjectName;
    private String enDescription;

    private String ruCategory;
    private String ruProjectName;
    private String ruDescription;

    //TODO
    //picture

    public String getProjectCategory(String lang) {
        return switch (lang) {
            case "en" -> enCategory;
            case "ru" -> ruCategory;
            default -> azCategory;
        };
    }

    public String getProjectDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }

    public String getProjectName(String lang) {
        return switch (lang) {
            case "en" -> enProjectName;
            case "ru" -> ruProjectName;
            default -> azProjectName;
        };
    }
}
