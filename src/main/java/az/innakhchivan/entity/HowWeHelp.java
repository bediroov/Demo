package az.innakhchivan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "how_we_help")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HowWeHelp {

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


    public String getAboutUsTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }

    public String getAboutUsDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }
}
