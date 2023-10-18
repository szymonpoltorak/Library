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
import pl.edu.pw.ee.library.utils.data.AddNewBookData;
import pl.edu.pw.ee.library.utils.data.DeleteBookData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookMapper.class, BookRepository.class})
class BookServiceTest {
    private final GetBookByIdData testData = TestDataBuilder.bookTestData();
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
        assertEquals(testData.bookResponse(), actual, "Should return book response of given book id : " + bookId);
    }

    @Test
    final void test_getBookById_shouldThrowExceptionOnNotExistingBook() {
        // given
        long bookId = -1L;

        // when


        // then
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId),
                "Should throw exception on not existing book id : " + bookId);
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
}
