package pl.edu.pw.ee.library.utils;

import org.mapstruct.factory.Mappers;
import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.entities.book.Book;
import pl.edu.pw.ee.library.utils.data.*;

import java.util.ArrayList;
import java.util.List;


import pl.edu.pw.ee.library.utils.data.ReturnBookData;


public final class TestDataBuilder {

    private static final BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

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

    public static ReturnBookData getReturnBookData_correct() {
        long bookId = 1L;
        Book preReturn = Book
                .builder()
                .author("Wojtek Tobolski")
                .title("title")
                .booksAvailable(9)
                .booksInStock(10)
                .bookId(bookId)
                .build();
        Book postReturn = Book
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

        return new ReturnBookData(bookId, preReturn, postReturn, bookResponse);
    }

    public static SearchByBookNameData searchByBookNameTestData() {
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

        return new SearchByBookNameData(bookList, bookResponseList);
    }

    public static ReturnBookData getReturnBookData_nothingToReturn() {
        long bookId = 1L;
        Book preReturn = Book
                .builder()
                .author("Wojtek Tobolski")
                .title("title")
                .booksAvailable(10)
                .booksInStock(10)
                .bookId(bookId)
                .build();

        return new ReturnBookData(bookId, preReturn, null, null);
    }

    public static BorrowBookData getBorrowBookData_correct() {
        long bookId = 1L;
        Book preBorrow = Book
                .builder()
                .author("generic author")
                .title("generic title")
                .booksAvailable(5)
                .booksInStock(5)
                .bookId(bookId)
                .build();
        Book postBorrow = Book
                .builder()
                .author("generic author")
                .title("generic title")
                .booksAvailable(4)
                .booksInStock(5)
                .bookId(bookId)
                .build();
        BookResponse bookResponse = BookResponse
                .builder()
                .author("generic author")
                .title("generic title")
                .booksAvailable(4)
                .booksInStock(5)
                .bookId(bookId)
                .build();

        return new BorrowBookData(bookId, preBorrow, postBorrow, bookResponse);
    }

    public static BorrowBookData getBorrowBookData_nothingToBorrow() {
        long bookId = 1L;
        Book preReturn = Book
                .builder()
                .author("generic author")
                .title("generic title")
                .booksAvailable(0)
                .booksInStock(5)
                .bookId(bookId)
                .build();
        return new BorrowBookData(bookId, preReturn, null, null);
    }
}
