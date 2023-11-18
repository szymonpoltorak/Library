package pl.edu.pw.ee.cinemabackend.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@NoArgsConstructor
public class AbstractApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3963531545627058168L;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    protected AbstractApiException(String message) {
        super(message);
    }

    protected AbstractApiException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractApiException(String message, HttpStatus httpStatus) {
        super(message);

        this.httpStatus = httpStatus;
    }

    protected AbstractApiException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);

        this.httpStatus = httpStatus;
    }
}
