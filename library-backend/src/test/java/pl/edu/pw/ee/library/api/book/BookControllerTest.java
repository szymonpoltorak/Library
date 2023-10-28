package pl.edu.pw.ee.library.api.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookService;
import pl.edu.pw.ee.library.exceptions.book.BookNotFoundException;
import pl.edu.pw.ee.library.utils.TestDataBuilder;
import pl.edu.pw.ee.library.utils.data.AddNewBookData;
import pl.edu.pw.ee.library.utils.data.BorrowBookData;
import pl.edu.pw.ee.library.utils.data.DeleteBookData;
import pl.edu.pw.ee.library.utils.data.GetBookByIdData;
import pl.edu.pw.ee.library.utils.data.ReturnBookData;
import pl.edu.pw.ee.library.utils.data.SearchByBookNameData;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookService.class)
class BookControllerTest {
    private static final String SHOULD_RETURN_BOOK_RESPONSE_OF_GIVEN_BOOK_ID_S = "Should return book response of given book id : %s";
    private static final String SHOULD_THROW_EXCEPTION_ON_NOT_EXISTING_BOOK_ID_S = "Should throw exception on not existing book id : %s";
    private final SearchByBookNameData searchByBookNameTestData = TestDataBuilder.searchByBookNameTestData();

    private final GetBookByIdData testData = TestDataBuilder.getBookByIdTestData();

    @InjectMocks
    private BookControllerImpl bookController;
    @Mock
    private BookService bookService;

    @Test
    final void test_getBookById_shouldReturnBook() {
        // given
        long bookId = 1L;

        // when
        when(bookService.getBookById(bookId))
                .thenReturn(testData.bookResponse());

        BookResponse actual = bookController.getBookById(bookId);

        // then
        assertEquals(testData.bookResponse(), actual,
                String.format(SHOULD_RETURN_BOOK_RESPONSE_OF_GIVEN_BOOK_ID_S, bookId));
    }

    @Test
    final void test_getBookById_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when
        when(bookService.getBookById(bookId))
                .thenThrow(BookNotFoundException.class);


        // then
        assertThrows(BookNotFoundException.class, () -> bookController.getBookById(bookId),
                String.format(SHOULD_THROW_EXCEPTION_ON_NOT_EXISTING_BOOK_ID_S, bookId));
    }

    @Test
    final void test_searchByBookName_shouldReturnBookList() {
        // given
        String title = "title";

        // when
        when(bookService.searchByBookName(title))
                .thenReturn(searchByBookNameTestData.bookResponseList());

        List<BookResponse> actual = bookController.searchByBookName(title);

        // then
        assertEquals(searchByBookNameTestData.bookResponseList(), actual,
                String.format("Should return book response list for a given title: %s", title));
    }

    @Test
    final void test_SearchByBookName_shouldThrowExceptionWhenGivenTitleIsNull() {
        // given
        String title = null;

        // when
        when(bookService.searchByBookName(title))
                .thenThrow(NullPointerException.class);

        // then
        assertThrows(NullPointerException.class,
                () -> bookController.searchByBookName(title),
                "Should throw exception when given title is null");
    }

    @Test
    final void test_returnBook_shouldReturnBook() {
        // given
        ReturnBookData data = TestDataBuilder.getReturnBookData_correct();
        long bookId = data.bookId();

        // when
        when(bookService.returnBook(bookId))
                .thenReturn(data.bookResponse());

        BookResponse actual = bookController.returnBook(bookId);

        // then
        assertEquals(data.bookResponse(), actual,
                String.format(SHOULD_RETURN_BOOK_RESPONSE_OF_GIVEN_BOOK_ID_S, bookId));
    }

    @Test
    final void test_addNewBook_shouldReturnSavedBookResponse() {
        //given
        AddNewBookData addNewBookData = TestDataBuilder.addNewBookTestData();

        //when
        when(bookService.addNewBook(addNewBookData.bookRequest()))
                .thenReturn(addNewBookData.bookResponse());

        BookResponse actual = bookController.addNewBook(addNewBookData.bookRequest());
        //then
        assertEquals(addNewBookData.bookResponse(), actual,
                String.format("Should return book response of title : %s", actual.title()));
    }

    @Test
    final void test_deleteBook_shouldThrowExceptionOnNonExistingBook() {
        //given
        long bookId = -1L;

        //when
        when(bookService.deleteBook(bookId))
                .thenThrow(BookNotFoundException.class);

        //then
        assertThrows(BookNotFoundException.class, () -> bookController.deleteBook(bookId),
                String.format(SHOULD_THROW_EXCEPTION_ON_NOT_EXISTING_BOOK_ID_S, bookId));
    }

    @Test
    final void test_deleteBook_shouldDeleteBookAndReturnIt() {
        //given
        DeleteBookData deleteBookData = TestDataBuilder.deleteBookTestData();
        long bookId = deleteBookData.bookId();

        //when
        when(bookService.deleteBook(bookId))
                .thenReturn(deleteBookData.deletedBook());

        BookResponse actual = bookController.deleteBook(bookId);

        //then
        assertEquals(deleteBookData.deletedBook(), actual,
                String.format(SHOULD_RETURN_BOOK_RESPONSE_OF_GIVEN_BOOK_ID_S, bookId));
    }

    @Test
    final void test_returnBook_shouldThrowWhenNothingToReturn() {
        // given
        ReturnBookData data = TestDataBuilder.getReturnBookData_nothingToReturn();
        long bookId = data.bookId();

        // when
        when(bookService.returnBook(bookId))
                .thenThrow(IllegalStateException.class);

        // then
        assertThrows(IllegalStateException.class, () -> bookController.returnBook(bookId),
                String.format("Should throw exception when there are not any books to return : %s", bookId));
    }

    @Test
    final void test_returnBook_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when
        when(bookService.returnBook(bookId))
                .thenThrow(BookNotFoundException.class);

        // then
        assertThrows(BookNotFoundException.class, () -> bookController.returnBook(bookId),
                String.format(SHOULD_THROW_EXCEPTION_ON_NOT_EXISTING_BOOK_ID_S, bookId));
    }


    @Test
    final void test_borrowBook_shouldBorrowBook() {
        // given
        BorrowBookData data = TestDataBuilder.getBorrowBookData_correct();
        long bookId = data.bookId();

        // when
        when(bookService.borrowBook(bookId))
                .thenReturn(data.bookResponse());

        BookResponse actual = bookController.borrowBook(bookId);

        // then
        assertEquals(data.bookResponse(), actual,
                String.format("Should borrow book response of given book id : %s", bookId));
    }

    @Test
    final void test_borrowBook_shouldThrowWhenNothingToBorrow() {
        // given
        BorrowBookData data = TestDataBuilder.getBorrowBookData_nothingToBorrow();
        long bookId = data.bookId();

        // when
        when(bookService.borrowBook(bookId))
                .thenThrow(IllegalStateException.class);

        // then
        assertThrows(IllegalStateException.class, () -> bookController.borrowBook(bookId),
                String.format("Should throw exception when there are no books to borrow  : %s", bookId));
    }

    @Test
    final void test_borrowBook_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when
        when(bookService.borrowBook(bookId))
                .thenThrow(BookNotFoundException.class);

        // then
        assertThrows(BookNotFoundException.class, () -> bookController.borrowBook(bookId),
                String.format(SHOULD_THROW_EXCEPTION_ON_NOT_EXISTING_BOOK_ID_S, bookId));
    }
}