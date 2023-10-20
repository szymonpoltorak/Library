package pl.edu.pw.ee.library.api.book;

import jakarta.validation.Valid;
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
    public final BookResponse addNewBook(@Valid BookRequest bookRequest) {
        log.info("Creating book of title {}", bookRequest.title());

        Book newBook = Book
                .builder()
                .author(bookRequest.author())
                .title(bookRequest.title())
                .booksInStock(bookRequest.booksInStock())
                .booksAvailable(bookRequest.booksInStock())
                .build();

        newBook = bookRepository.save(newBook);
        log.info("Returning book response of : {}", newBook);

        return bookMapper.toBookResponse(newBook);
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
        if(bookName == null) {
            throw new NullPointerException("bookName cannot be null");
        }

        log.info("Searching books with name : {}", bookName);

        List<Book> bookList = bookRepository.findByTitle(bookName);

        if(bookList.isEmpty()) {
            throw new BookNotFoundException(String.format("There are no books matching title '%s'", bookName));
        }

        log.info("Returning bookResponseList of : {}", bookList);

        return bookMapper.toBookResponseList(bookList);
    }

    @Override
    public final BookResponse borrowBook(long bookId) {
        log.info("Borrowing book of id : {}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id '%s' does not exist!", bookId)));

        book.borrowBook();

        Book borrowedBook = bookRepository.save(book);

        log.info("Returning book response of : {}", book);

        return bookMapper.toBookResponse(borrowedBook);
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
        log.info("Deleting book of id {}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id '%s' does not exist!", bookId)));

        bookRepository.deleteById(bookId);

        log.info("Returning book response of : {}", book);

        return bookMapper.toBookResponse(book);
    }
}
