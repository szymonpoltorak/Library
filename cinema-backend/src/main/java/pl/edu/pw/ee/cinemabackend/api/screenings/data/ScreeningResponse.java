package pl.edu.pw.ee.cinemabackend.api.screenings.data;

import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScreeningResponse(Movie movie, LocalDate dayOfScreening, LocalTime hourOfScreening) {
}
