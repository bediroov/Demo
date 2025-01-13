package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrepreneur")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BecomingAnEntrepreneurInNakhinvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String azTitle;
    @Column(columnDefinition = "TEXT")
    private String azDescription;

    private String enTitle;
    @Column(columnDefinition = "TEXT")
    private String enDescription;

    private String ruTitle;
    @Column(columnDefinition = "TEXT")
    private String ruDescription;
    private String iconUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public String getEntrepreneurTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getEntrepreneurDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
