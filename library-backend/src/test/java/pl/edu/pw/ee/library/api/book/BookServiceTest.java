package pl.edu.pw.ee.library.api.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.entities.book.interfaces.BookRepository;
import pl.edu.pw.ee.library.exceptions.book.BookNotFoundException;
import pl.edu.pw.ee.library.utils.data.GetBookByIdData;
import pl.edu.pw.ee.library.utils.TestDataBuilder;
import pl.edu.pw.ee.library.utils.data.ReturnBookData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookMapper.class, BookRepository.class})
class BookServiceTest {

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

}
