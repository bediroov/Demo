package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "map_data")
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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    private String azGeneralInfo;
    private String enGeneralInfo;
    private String ruGeneralInfo;

    public String getMapDatageneralInfo(String lang) {
        return switch (lang) {
            case "en" -> enGeneralInfo;
            case "ru" -> ruGeneralInfo;
            default -> azGeneralInfo;
        };
    }
//
//
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
