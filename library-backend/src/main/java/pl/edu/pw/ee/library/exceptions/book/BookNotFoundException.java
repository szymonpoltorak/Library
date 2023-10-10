package pl.edu.pw.ee.library.exceptions.book;

import java.io.Serial;

public class BookNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 9200225091079622701L;

    public BookNotFoundException(String message) {
        super(message);
    }
}
