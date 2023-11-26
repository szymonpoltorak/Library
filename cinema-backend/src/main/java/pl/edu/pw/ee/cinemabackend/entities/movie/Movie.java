package pl.edu.pw.ee.cinemabackend.entities.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movieId;

    private String title;

    private String description;

    private String timeDuration;

    private int minimalAge;

    @OneToMany
    @ToString.Exclude
    private List<Screening> screenings;
}
