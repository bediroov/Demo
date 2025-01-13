package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "nakhinvest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhyNakhinvest {

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
    private String imageUrl;

    public String getWhyNakhinvestTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getWhyNakhinvestDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
