package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "who_are_we")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhoAreWe {
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



    public String getWhoAreWeTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getWhoAreWeDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
