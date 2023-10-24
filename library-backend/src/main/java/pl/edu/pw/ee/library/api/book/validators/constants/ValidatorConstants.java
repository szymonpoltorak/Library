package pl.edu.pw.ee.library.api.book.validators.constants;

public final class ValidatorConstants {

    public static final String BOOK_REQUEST_NULL = "Book request cannot be null";

    public static final String STRING_NULL = "Given string is null";

    public static final String STRING_EMPTY_BLANK = "String cannot be empty or blank";

    public static final String TOO_FEW_BOOKS = "Amount of books in stock must be at least 1";

    public static final String TOO_MANY_BOOKS = "Amount of books in stock must not exceed 100";

    private ValidatorConstants() {
    }
}
