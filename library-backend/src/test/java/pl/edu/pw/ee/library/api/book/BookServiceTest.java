package pl.edu.pw.ee.library.api.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.entities.book.interfaces.BookRepository;
import pl.edu.pw.ee.library.exceptions.book.BookNotFoundException;
import pl.edu.pw.ee.library.utils.data.*;
import pl.edu.pw.ee.library.utils.TestDataBuilder;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookMapper.class, BookRepository.class})
class BookServiceTest {
    private final SearchByBookNameData searchByBookNameTestData = TestDataBuilder.searchByBookNameTestData();

    private final GetBookByIdData testData = TestDataBuilder.getBookByIdTestData();

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @Test
    final void test_getBookById_shouldReturnBook() {
        // given
        long bookId = 1L;

        // when
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(testData.book()));
        when(bookMapper.toBookResponse(testData.book()))
                .thenReturn(testData.bookResponse());

        BookResponse actual = bookService.getBookById(bookId);

        // then
        assertEquals(testData.bookResponse(), actual,
                String.format("Should return book response of given book id : %s", bookId));
    }

    @Test
    final void test_getBookById_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when


        // then
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId),
                String.format("Should throw exception on not existing book id : %s", bookId));
    }

    @Test
    final void test_searchByBookName_shouldReturnBookList() {
        // given
        String title = "title";

        // when
        when(bookRepository.findByTitle(title))
                .thenReturn(searchByBookNameTestData.bookList());
        when(bookMapper.toBookResponse(searchByBookNameTestData.bookList().get(0)))
                .thenReturn(searchByBookNameTestData.bookResponseList().get(0));
        when(bookMapper.toBookResponse(searchByBookNameTestData.bookList().get(1)))
                .thenReturn(searchByBookNameTestData.bookResponseList().get(1));

        List<BookResponse> actual = bookService.searchByBookName(title);

        // then
        assertEquals(searchByBookNameTestData.bookResponseList(), actual,
                String.format("Should return book response list for a given title: %s", title));
    }

    @Test
    final void test_SearchByBookName_shouldThrowExceptionWhenGivenTitleIsNull() {
        // given
        String title = null;

        // when


        // then
        assertThrows(NullPointerException.class,
                () -> bookService.searchByBookName(title),
                "Should throw exception when given title is null");
    }

    @Test
    final void test_returnBook_shouldReturnBook() {
        // given
        ReturnBookData data = TestDataBuilder.getReturnBookData_correct();
        long bookId = data.bookId();

        // when
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(data.preReturn()));
        when(bookRepository.save(data.postReturn()))
                .thenReturn(data.postReturn());
        when(bookMapper.toBookResponse(data.postReturn()))
                .thenReturn(data.bookResponse());

        BookResponse actual = bookService.returnBook(bookId);

        // then
        assertEquals(data.bookResponse(), actual,
                String.format("Should return book response of given book id : %s", bookId));
    }

    @Test
    final void test_addNewBook_shouldReturnSavedBookResponse() {
        //given
        AddNewBookData addNewBookData = TestDataBuilder.addNewBookTestData();

        //when
        when(bookMapper.toBookResponse(addNewBookData.book()))
                .thenReturn(addNewBookData.bookResponse());
        when(bookRepository.save(addNewBookData.book()))
                .thenReturn(addNewBookData.book());

        BookResponse actual = bookService.addNewBook(addNewBookData.bookRequest());
        //then
        assertEquals(addNewBookData.bookResponse(), actual,
                "Should return book response of title : " + actual.title());
    }

    @Test
    final void test_deleteBook_shouldThrowExceptionOnNonExistingBook() {
        //given
        long bookId = -1;

        //when

        //then
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId),
                String.format("Should throw exception on not existing book id : %s", bookId));
    }

    @Test
    final void test_deleteBook_shouldDeleteBookAndReturnIt() {
        //given
        DeleteBookData deleteBookData = TestDataBuilder.deleteBookTestData();
        long bookId = deleteBookData.bookId();

        //when
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(deleteBookData.bookToDelete()));
        when(bookMapper.toBookResponse(deleteBookData.bookToDelete()))
                .thenReturn(deleteBookData.deletedBook());

        BookResponse actual = bookService.deleteBook(bookId);

        //then
        assertEquals(deleteBookData.deletedBook(), actual,
                String.format("Should return book response of given book id : %s", bookId));
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    final void test_returnBook_shouldThrowWhenNothingToReturn() {
        // given
        ReturnBookData data = TestDataBuilder.getReturnBookData_nothingToReturn();
        long bookId = data.bookId();

        // when
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(data.preReturn()));

        // then
        assertThrows(IllegalStateException.class, () -> bookService.returnBook(bookId),
                String.format("Should throw exception when there are not any books to return : %s", bookId));
    }

    @Test
    final void test_returnBook_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when

        // then
        assertThrows(BookNotFoundException.class, () -> bookService.returnBook(bookId),
                String.format("Should throw exception on not existing book id : %s", bookId));
    }


    @Test
    final void test_borrowBook_shouldBorrowBook() {
        // given
        BorrowBookData data = TestDataBuilder.getBorrowBookData_correct();
        long bookId = data.bookId();

        // when
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(data.preBorrow()));
        when(bookRepository.save(data.postBorrow()))
                .thenReturn(data.postBorrow());
        when(bookMapper.toBookResponse(data.postBorrow()))
                .thenReturn(data.bookResponse());

        BookResponse actual = bookService.borrowBook(bookId);

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
        when(bookRepository.findById(bookId))
                .thenReturn(Optional.ofNullable(data.preBorrow()));

        // then
        assertThrows(IllegalStateException.class, () -> bookService.borrowBook(bookId),
                String.format("Should throw exception when there are no books to borrow  : %s", bookId));
    }

    @Test
    final void test_borrowBook_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when

        // then
        assertThrows(BookNotFoundException.class, () -> bookService.borrowBook(bookId),
                String.format("Should throw exception on not existing book id : %s", bookId));
    }
}
