package pl.edu.pw.ee.cinemabackend.entities.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movieId;

    private String title;

    private String description;

    private String timeDuration;

    private int minimalAge;
}
