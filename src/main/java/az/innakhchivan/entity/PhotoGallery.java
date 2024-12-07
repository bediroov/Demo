package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "photo_gallery")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGallery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;
}
