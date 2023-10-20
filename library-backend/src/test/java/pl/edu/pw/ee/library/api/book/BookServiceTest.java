package pl.edu.pw.ee.library.api.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.entities.book.interfaces.BookRepository;
import pl.edu.pw.ee.library.exceptions.book.BookNotFoundException;
import pl.edu.pw.ee.library.utils.BookTestData;
import pl.edu.pw.ee.library.utils.SearchByBookNameTestData;
import pl.edu.pw.ee.library.utils.TestDataBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {
    private final BookTestData testData = TestDataBuilder.bookTestData();

    private final SearchByBookNameTestData searchByBookNameTestData = TestDataBuilder.searchByBookNameTestData();
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
    final void test_searchByBookName_shouldReturnBookList() {
        // given
        String title = "title";

        // when
        when(bookRepository.findByTitle(title))
                .thenReturn(searchByBookNameTestData.bookList());
        when(bookMapper.toBookResponseList(searchByBookNameTestData.bookList()))
                .thenReturn(searchByBookNameTestData.bookResponseList());

        List<BookResponse> actual = bookService.searchByBookName(title);

        // then
        assertEquals(searchByBookNameTestData.bookResponseList(), actual,
                "Should return book response list for a given title: " + title);
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
    final void test_SearchByBookName_shouldThrowExceptionWhenNoBooksOfThisTitle() {
        // given
        String title = "title";

        // when

        // then
        assertThrows(BookNotFoundException.class,
                () -> bookService.searchByBookName(title),
                "Should throw exception when there aren't any books with given name");
    }
}
