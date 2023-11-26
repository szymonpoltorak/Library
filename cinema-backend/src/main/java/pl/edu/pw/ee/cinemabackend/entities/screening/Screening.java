package pl.edu.pw.ee.cinemabackend.entities.screening;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Screening {
    @Id
    @GeneratedValue
    private long screeningId;

    @ManyToOne
    private Movie movie;

    private LocalDate dayOfScreening;

    private LocalTime hourOfScreening;
}
