package pl.edu.pw.ee.library.utils;


import org.mapstruct.factory.Mappers;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.entities.book.Book;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    private static final BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    private TestDataBuilder() {
    }

    public static BookTestData bookTestData() {
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

        return new BookTestData(book, bookResponse);
    }

    public static SearchByBookNameTestData searchByBookNameTestData() {
        List<Book> bookList = new ArrayList<>();
        List<BookResponse> bookResponseList = new ArrayList<>();

        long bookId = 1L;
        Book book1 = Book
                .builder()
                .author("Szymon Tarkowski")
                .title("title")
                .booksAvailable(10)
                .booksInStock(10)
                .bookId(bookId)
                .build();

        BookResponse bookResponse1 = MAPPER.toBookResponse(book1);

        bookList.add(book1);
        bookResponseList.add(bookResponse1);

        bookId = 2L;

        Book book2 = Book
                .builder()
                .author("Jacek Gwiazdka")
                .title("title")
                .booksAvailable(10)
                .booksInStock(10)
                .bookId(bookId)
                .build();

        BookResponse bookResponse2 = MAPPER.toBookResponse(book2);

        bookList.add(book2);
        bookResponseList.add(bookResponse2);

        return new SearchByBookNameTestData(bookList, bookResponseList);
    }
}
