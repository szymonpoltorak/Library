package pl.edu.pw.ee.library.api.book.data.constants;

public final class RecordConstants {

    public static final String TITLE_NOT_NULL_MESSAGE = "Title cannot be null";
    public static final String TITLE_NOT_BLANK_MESSAGE = "Title cannot be blank";
    public static final String AUTHOR_NOT_NULL_MESSAGE = "Author cannot be null";
    public static final String AUTHOR_NOT_BLANK_MESSAGE = "Author cannot be blank";
    public static final String BOOKS_IN_STOCK_MESSAGE = "Amount of books in stock must be at least 1";
    public static final int MINIMUM_AMOUNT_OF_BOOKS = 1;

    private RecordConstants() {
    }
}
