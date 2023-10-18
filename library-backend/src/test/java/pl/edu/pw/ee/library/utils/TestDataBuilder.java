package pl.edu.pw.ee.library.utils;


import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;
import pl.edu.pw.ee.library.utils.data.AddNewBookData;
import pl.edu.pw.ee.library.utils.data.DeleteBookData;
import pl.edu.pw.ee.library.utils.data.GetBookByIdData;

public class TestDataBuilder {
    private TestDataBuilder() {
    }

    public static GetBookByIdData bookTestData() {
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

    public static DeleteBookData deleteBookTestData() {
        long bookId = 1L;
        Book bookToDelete = Book
                .builder()
                .bookId(bookId)
                .title("title")
                .author("Wojtek Tobolski")
                .booksInStock(10)
                .booksAvailable(10)
                .build();

        BookResponse deletedBook = BookResponse
                .builder()
                .bookId(bookId)
                .title("title")
                .author("Wojtek Tobolski")
                .booksInStock(10)
                .booksAvailable(10)
                .build();


        return new DeleteBookData(bookId, bookToDelete, deletedBook);
    }

    public static AddNewBookData addNewBookTestData(){
        long bookId = 0L;
        BookRequest bookRequest = BookRequest
                .builder()
                .author("Wojtek Tobolski")
                .title("title")
                .booksInStock(10)
                .build();

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

        return new AddNewBookData(book, bookResponse, bookRequest);
    }
}
