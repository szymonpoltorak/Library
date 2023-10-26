package pl.edu.pw.ee.library.utils.data;

import org.springframework.http.ResponseEntity;
import pl.edu.pw.ee.library.api.exceptions.ExceptionResponse;

public record HandleRuntimeExceptionData<T extends RuntimeException>(T exceptionInstance, ResponseEntity<ExceptionResponse> response) {
}
