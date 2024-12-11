package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "photo_gallery")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @URL
    private String imageUrl;
}
