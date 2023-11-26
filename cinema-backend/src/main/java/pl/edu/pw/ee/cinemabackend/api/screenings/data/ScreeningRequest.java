package pl.edu.pw.ee.cinemabackend.api.screenings.data;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ScreeningRequest(long movieId,
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dayOfScreening,
                               @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hourOfScreening) {
}
