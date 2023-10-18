package pl.edu.pw.ee.library.api.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookMapper;
import pl.edu.pw.ee.library.api.book.interfaces.BookService;
import pl.edu.pw.ee.library.entities.book.Book;
import pl.edu.pw.ee.library.entities.book.interfaces.BookRepository;
import pl.edu.pw.ee.library.exceptions.book.BookNotFoundException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public final BookResponse addNewBook(BookRequest bookRequest) {
        return null;
    }

    @Override
    public final BookResponse getBookById(long bookId) {
        log.info("Getting book of id : {}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id '%s' does not exist!", bookId)));

        log.info("Returning book response of : {}", book);

        return bookMapper.toBookResponse(book);
    }

    @Override
    public final List<BookResponse> searchByBookName(String bookName) {
        return null;
    }

    @Override
    public final BookResponse borrowBook(long bookId) {
        return null;
    }

    @Override
    public final BookResponse returnBook(long bookId) {
        log.info("Returning book of id : {}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id '%s' does not exist!", bookId)));

        book.returnBook();

        Book returnedBook = bookRepository.save(book);

        log.info("Returning book response of : {}", book);

        return bookMapper.toBookResponse(returnedBook);
    }

    @Override
    public final BookResponse deleteBook(long bookId) {
        return null;
    }
}
