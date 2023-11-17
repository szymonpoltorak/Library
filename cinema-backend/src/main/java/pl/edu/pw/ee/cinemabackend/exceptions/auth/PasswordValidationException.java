package pl.edu.pw.ee.cinemabackend.exceptions.auth;

import jakarta.validation.ValidationException;

public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }
}
