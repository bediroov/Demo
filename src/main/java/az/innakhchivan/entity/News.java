package az.innakhchivan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

    @NotNull
    private String azTitle;
    @NotNull
    private String azDescription;
    @NotNull
    private String azAuthor;

    private String enTitle;
    private String enDescription;
    private String enAuthor;

    private String ruTitle;
    private String ruDescription;
    private String ruAuthor;

    private String imageName;
    private String ImageType;

//    @Lob
//    @Column(name = "image_data")
//    private byte[] imageData;

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

    public String getNewsAuthor(String lang) {
        return switch (lang) {
            case "en" -> enAuthor;
            case "ru" -> ruAuthor;
            default -> azAuthor;
        };
    }

}
