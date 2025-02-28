package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MapData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String azTitle;
    private String azDescription;

    private String enTitle;
    private String enDescription;

    private String ruTitle;
    private String ruDescription;

    private String location;
    private String area;
    private String averageSalary;
    private Integer population;
    private String iconUrl;


    @Column(name = "settlement_count")
    private Integer settlementCount;

    @Column(name = "village_count")
    private Integer villageCount;

    @Column(name = "city_admin_area")
    private Integer cityAdminArea;

    @Column(name = "distance_from_baku")
    private String distanceFromBaku;

    @Column(name = "general_info", columnDefinition = "TEXT")
    private String generalInfo;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;


    public String getMapDataDescription(String lang) {
        return switch (lang) {
            case "en" -> enDescription;
            case "ru" -> ruDescription;
            default -> azDescription;
        };
    }

    public String getMapDataTitle(String lang) {
        return switch (lang) {
            case "en" -> enTitle;
            case "ru" -> ruTitle;
            default -> azTitle;
        };
    }
}
