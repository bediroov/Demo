
package az.innakhchivan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    private String azTitle;
    private String azDescription;

    private String enTitle;
    private String enDescription;

    private String ruTitle;
    private String ruDescription;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Project(Long id, String azTitle, String azDescription, String enTitle, String enDescription, String ruTitle, String ruDescription, String imageUrl, Category category, LocalDate createdAt) {
        this.id = id;
        this.azTitle = azTitle;
        this.azDescription = azDescription;
        this.enTitle = enTitle;
        this.enDescription = enDescription;
        this.ruTitle = ruTitle;
        this.ruDescription = ruDescription;
        this.imageUrl = imageUrl;
        this.category = category;
        this.setCreatedAt(createdAt);
    }

    public String getProjectTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getProjectDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
