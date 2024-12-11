package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "regions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uniqueKey;

    @Column(nullable = false)
    private String azName;

    @Column(nullable = false)
    private String enName;

    @Column(nullable = false)
    private String ruName;



    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MapData> mapDataList;

    public String getRegionName(String lang) {
        return switch (lang) {
            case "en" -> enName;
            case "ru" -> ruName;
            default -> getAzName();
        };
    }
}
