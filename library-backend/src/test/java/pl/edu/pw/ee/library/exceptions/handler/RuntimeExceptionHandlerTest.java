package pl.edu.pw.ee.library.exceptions.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import pl.edu.pw.ee.library.api.exceptions.ExceptionResponse;
import pl.edu.pw.ee.library.utils.TestDataBuilder;
import pl.edu.pw.ee.library.utils.data.HandleRuntimeExceptionData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {RuntimeExceptionHandler.class})
public class RuntimeExceptionHandlerTest {

    private static final String SHOULD_CORRECTLY_HANDLE_ILLEGAL_ARGUMENT_EXCEPTION =
            "Should correctly handle illegal argument exception.";

    @Autowired
    private RuntimeExceptionHandler runtimeExceptionHandler;

    @Test
    final void test_shouldCorrectlyHandleIllegalArgumentException() {
        // given
        HandleRuntimeExceptionData<IllegalArgumentException> testData = TestDataBuilder.getHandleRuntimeExceptionData_IllegalArgumentException();

        // when
        ResponseEntity<ExceptionResponse> actual = runtimeExceptionHandler.handleRuntimeException(testData.exceptionInstance());

        // then
        assertEquals(testData.response(), actual, SHOULD_CORRECTLY_HANDLE_ILLEGAL_ARGUMENT_EXCEPTION);
    }

}
