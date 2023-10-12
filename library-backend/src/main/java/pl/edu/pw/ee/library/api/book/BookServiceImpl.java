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
        log.info("Creating book of title {}", bookRequest.title());

        if(bookRequest.author().isBlank()){
            throw new IllegalArgumentException("Book author field cannot be blank");
        }

        if(bookRequest.title().isBlank()){
            throw new IllegalArgumentException("Book title field cannot be blank");
        }

        if(bookRequest.booksInStock()<=0){
            throw new IllegalArgumentException("Amount of books in stock must be a positive integer");
        }

        Book newBook = new Book();
        newBook.setTitle(bookRequest.title());
        newBook.setAuthor(bookRequest.author());
        newBook.setBooksInStock(bookRequest.booksInStock());
        newBook.setBooksAvailable(bookRequest.booksInStock());

        bookRepository.save(newBook);

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
        return null;
    }

    @Override
    public final BookResponse borrowBook(long bookId) {
        return null;
    }

    @Override
    public final BookResponse returnBook(long bookId) {
        return null;
    }

    @Override
    public final BookResponse deleteBook(long bookId) {
        log.info("Deleting book of id {}", bookId);

        Book book =  bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id '%s' does not exist!", bookId)));

        bookRepository.deleteById(bookId);
        log.info("Returning book response of : {}", book);
        return bookMapper.toBookResponse(book);
    }
}
