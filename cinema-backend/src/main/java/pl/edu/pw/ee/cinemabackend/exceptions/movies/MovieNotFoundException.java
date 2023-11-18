package pl.edu.pw.ee.cinemabackend.exceptions.movies;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.edu.pw.ee.cinemabackend.exceptions.AbstractApiException;

import java.io.Serial;

@NoArgsConstructor
public class MovieNotFoundException extends AbstractApiException {
    @Serial
    private static final long serialVersionUID = 1136617089313191256L;

    public MovieNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
