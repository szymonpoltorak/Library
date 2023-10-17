package pl.edu.pw.ee.library.utils;


import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;
import pl.edu.pw.ee.library.utils.data.GetBookByIdData;

public class TestDataBuilder {
    private TestDataBuilder() {
    }

    public static GetBookByIdData getBookByIdTestData() {
        long bookId = 1L;
        Book book = Book
                .builder()
                .author("Wojtek Tobolski")
                .title("title")
                .booksAvailable(10)
                .booksInStock(10)
                .bookId(bookId)
                .build();
        BookResponse bookResponse = BookResponse
                .builder()
                .author("Wojtek Tobolski")
                .title("title")
                .booksAvailable(10)
                .booksInStock(10)
                .bookId(bookId)
                .build();

        return new GetBookByIdData(book, bookResponse);
    }
}
