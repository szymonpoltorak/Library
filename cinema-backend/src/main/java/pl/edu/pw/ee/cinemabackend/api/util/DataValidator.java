package pl.edu.pw.ee.cinemabackend.api.util;

import org.springframework.stereotype.Component;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;

import static pl.edu.pw.ee.cinemabackend.api.movies.constants.ValidationConstants.*;

@Component
public class DataValidator {

    public void validateMovieRequest(MovieRequest movieRequest) {
        if (movieRequest.title() == null || movieRequest.title().isBlank()) {
            throw new IllegalArgumentException(INVALID_TITLE_MESSAGE);
        }
        if (movieRequest.description() == null || movieRequest.description().isBlank()) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_MESSAGE);
        }
        if (movieRequest.timeDuration() == null || movieRequest.timeDuration().isBlank()) {
            throw new IllegalArgumentException(INVALID_DURATION_MESSAGE);
        }
        if (movieRequest.minimalAge() < 0) {
            throw new IllegalArgumentException(INVALID_MIN_AGE_MESSAGE);
        }
    }

}
