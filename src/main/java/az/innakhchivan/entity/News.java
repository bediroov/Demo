package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News extends BaseEntity implements Serializable {
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

    private String optional;

    public News(Long id, String azTitle, String azDescription, String enTitle, String enDescription,
                String ruTitle, String ruDescription, String imageUrl, String optional,
                LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.azTitle = azTitle;
        this.azDescription = azDescription;
        this.enTitle = enTitle;
        this.enDescription = enDescription;
        this.ruTitle = ruTitle;
        this.ruDescription = ruDescription;
        this.imageUrl = imageUrl;
        this.optional = optional;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public String getNewsTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getNewsDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
