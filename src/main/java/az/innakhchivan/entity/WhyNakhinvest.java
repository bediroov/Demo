package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String title;
    private String description;

    //picture
}
