package pl.edu.pw.ee.cinemabackend.exceptions.screenings;

import org.springframework.http.HttpStatus;
import pl.edu.pw.ee.cinemabackend.exceptions.AbstractApiException;

import java.io.Serial;

public class ScreeningNotFoundException extends AbstractApiException {
    @Serial
    private static final long serialVersionUID = 1337328764234098632L;

    public ScreeningNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
