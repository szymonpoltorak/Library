package pl.edu.pw.ee.library.api.book.interfaces;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {BookMapper.class})
class BookMapperTest {
    @InjectMocks
    private BookMapperImpl bookMapper;

    @Test
    void test_toBookResponse_shouldReturnNullWhenBookIsNull() {
        // given
        Book book = null;

        // when
        BookResponse actual = bookMapper.toBookResponse(book);

        // then
        assertNull(actual, String.format("Result should be null but it is : %s", actual));
    }

    @Test
    void test_toBookResponse_shouldNotReturnNullWhenBookIsNotNull() {
        // given
        Book book = new Book();

        // when
        BookResponse actual = bookMapper.toBookResponse(book);

        // then
        assertNotNull(actual, "Book mappers result was null but it should not be.");
    }
}
