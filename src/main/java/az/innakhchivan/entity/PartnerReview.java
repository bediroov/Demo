package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "partner_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String azPartnerName;
    @Column(columnDefinition = "TEXT")
    private String azComment;

    private String enPartnerName;
    @Column(columnDefinition = "TEXT")
    private String enComment;

    private String ruPartnerName;
    @Column(columnDefinition = "TEXT")
    private String ruComment;

    private String iconUrl;

    public String getPartnerFullName(String lang) {
        return switch (lang) {
            case "en" -> enPartnerName;
            case "ru" -> ruPartnerName;
            default -> azPartnerName;
        };
    }

    public String getPartnerComment(String lang) {
        return switch (lang) {
            case "en" -> enComment;
            case "ru" -> ruComment;
            default -> azComment;
        };

    }
}
