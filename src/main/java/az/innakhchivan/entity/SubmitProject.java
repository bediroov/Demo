package az.innakhchivan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Table(name = "submit_project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String message;
    @URL
    private String fileUrl;

    @Column(name = "submit_date")
    @CreationTimestamp
    private LocalDate submitDate;
}
