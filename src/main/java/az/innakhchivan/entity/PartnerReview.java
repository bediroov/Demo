package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partner_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String azPartnerName;
    private String azComment;

    private String enPartnerName;
    private String enComment;

    private String ruPartnerName;
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
