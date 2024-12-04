package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "news_translation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String languageCode; // (az, en, ru)

    @OneToMany(mappedBy = "newTranslation", cascade = CascadeType.ALL)
    private List<News> news;
}
