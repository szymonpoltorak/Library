package pl.edu.pw.ee.library.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.pw.ee.library.api.exceptions.ExceptionResponse;

@ControllerAdvice
@Slf4j
public class RuntimeExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception) {
        String message = exception.getMessage();
        String exceptionClassName = exception.getClass().getSimpleName();
        ExceptionResponse response = new ExceptionResponse(message, exceptionClassName);

        log.error("During work exception occurred. Here are the details: \n{}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
