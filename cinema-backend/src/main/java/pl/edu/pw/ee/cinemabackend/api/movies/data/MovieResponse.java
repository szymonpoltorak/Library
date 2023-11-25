package pl.edu.pw.ee.cinemabackend.api.movies.data;

import lombok.Builder;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;

import java.util.List;

@Builder
public record MovieResponse(long movieId, String title, String description, String timeDuration, int minimalAge,
                            List<Screening> screenings) {
}
