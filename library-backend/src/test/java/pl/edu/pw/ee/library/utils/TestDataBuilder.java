package pl.edu.pw.ee.library.utils;


import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.api.exceptions.ExceptionResponse;
import pl.edu.pw.ee.library.entities.book.Book;
import pl.edu.pw.ee.library.utils.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public static AddNewBookData addNewBookTestData() {
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

    public static HandleRuntimeExceptionData<IllegalArgumentException> getHandleRuntimeExceptionData_IllegalArgumentException() {
        String message = "Niepoprawny argument funkcji";
        IllegalArgumentException instance = new IllegalArgumentException(message);
        String className = instance.getClass().getSimpleName();
        ResponseEntity<ExceptionResponse> response = new ResponseEntity<>(new ExceptionResponse(message, className), HttpStatus.BAD_REQUEST);
        return new HandleRuntimeExceptionData<>(instance, response);
    }


    public static CorrectBookRequestData getCorrectBookRequestData(){
        BookRequest bookRequest1 = BookRequest
                .builder()
                .author("Autor 1")
                .title("Tytuł 1")
                .booksInStock(5)
                .build();
        BookRequest bookRequest2 = BookRequest
                .builder()
                .author("1")
                .title("1")
                .booksInStock(1)
                .build();
        BookRequest bookRequest3 = BookRequest
                .builder()
                .author("a")
                .title("b")
                .booksInStock(100)
                .build();
        BookRequest bookRequest4 = BookRequest
                .builder()
                .author("%$aa")
                .title("fds%$#")
                .booksInStock(45)
                .build();

        return new CorrectBookRequestData(Stream.of(bookRequest1,
                 bookRequest2,
                 bookRequest3));
    }

    public static IncorrectBookRequestData getIncorrectBookRequestData(){
        BookRequest bookRequest1 = BookRequest
                .builder()
                .author("")
                .title("Tytuł 1")
                .booksInStock(5)
                .build();
        BookRequest bookRequest2 = BookRequest
                .builder()
                .author("   ")
                .title("Tytuł 1")
                .booksInStock(5)
                .build();
        BookRequest bookRequest3 = BookRequest
                .builder()
                .author(null)
                .title("Tytuł 1")
                .booksInStock(5)
                .build();
        BookRequest bookRequest4 = BookRequest
                .builder()
                .author("autor 1")
                .title("")
                .booksInStock(5)
                .build();
        BookRequest bookRequest5 = BookRequest
                .builder()
                .author("autor 1")
                .title("    ")
                .booksInStock(5)
                .build();
        BookRequest bookRequest6 = BookRequest
                .builder()
                .author("autor 1")
                .title(null)
                .booksInStock(5)
                .build();
        BookRequest bookRequest7 = BookRequest
                .builder()
                .author("autor 1")
                .title("Tytuł 1")
                .booksInStock(0)
                .build();
        BookRequest bookRequest8 = BookRequest
                .builder()
                .author("autor 1")
                .title("Tytuł 1")
                .booksInStock(-1)
                .build();
        BookRequest bookRequest9 = BookRequest
                .builder()
                .author(null)
                .title(null)
                .booksInStock(0)
                .build();
        BookRequest bookRequest10 = BookRequest
                .builder()
                .author("")
                .title("")
                .booksInStock(2)
                .build();
        BookRequest bookRequest11 = BookRequest
                .builder()
                .author("  ")
                .title("  ")
                .booksInStock(2)
                .build();
        BookRequest bookRequest12 = BookRequest
                .builder()
                .author("autor 1")
                .title("Tytuł 1")
                .booksInStock(101)
                .build();

        return new IncorrectBookRequestData(Stream.of(bookRequest1,
                bookRequest2,
                bookRequest3,
                bookRequest4,
                bookRequest5,
                bookRequest6,
                bookRequest7,
                bookRequest8,
                bookRequest9,
                bookRequest10,
                bookRequest11,
                bookRequest12,
                null));
    }

    public static ExpectedBookData getExpectedBookData(BookRequest bookRequest){
        BookResponse expected = BookResponse
                .builder()
                .booksAvailable(bookRequest.booksInStock())
                .booksInStock(bookRequest.booksInStock())
                .title(bookRequest.title())
                .author(bookRequest.author())
                .bookId(0L)
                .build();

        Book book = Book
                .builder()
                .booksAvailable(bookRequest.booksInStock())
                .booksInStock(bookRequest.booksInStock())
                .title(bookRequest.title())
                .author(bookRequest.author())
                .bookId(0L)
                .build();

        return new ExpectedBookData(book, expected);
    }
}
