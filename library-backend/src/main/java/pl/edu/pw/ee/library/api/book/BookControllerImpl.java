package pl.edu.pw.ee.library.api.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.api.book.interfaces.BookService;
import pl.edu.pw.ee.library.api.book.interfaces.BookController;

import java.util.List;

import static pl.edu.pw.ee.library.api.book.constants.BookMappings.ADD_NEW_BOOK_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.BOOKS_API_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.BORROW_BOOK_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.DELETE_BOOK_BY_ID_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.GET_BOOKS_BY_TITLE_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.GET_BOOK_BY_ID_MAPPING;
import static pl.edu.pw.ee.library.api.book.constants.BookMappings.RETURN_BOOK_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BOOKS_API_MAPPING)
public class BookControllerImpl implements BookController {
    private final BookService bookService;

    @Override
    @PostMapping(value = ADD_NEW_BOOK_MAPPING)
    public final BookResponse addNewBook(@RequestBody BookRequest bookRequest) {
        return bookService.addNewBook(bookRequest);
    }

    @Override
    @GetMapping(value = GET_BOOK_BY_ID_MAPPING)
    public final BookResponse getBookById(@RequestParam long bookId) {
        return bookService.getBookById(bookId);
    }

    @Override
    @GetMapping(value = GET_BOOKS_BY_TITLE_MAPPING)
    public final List<BookResponse> searchByBookName(@RequestParam String bookName) {
        return bookService.searchByBookName(bookName);
    }

    @Override
    @PatchMapping(value = BORROW_BOOK_MAPPING)
    public final BookResponse borrowBook(@RequestParam long bookId) {
        return bookService.borrowBook(bookId);
    }

    @Override
    @PatchMapping(value = RETURN_BOOK_MAPPING)
    public final BookResponse returnBook(@RequestParam long bookId) {
        return bookService.returnBook(bookId);
    }

    @Override
    @DeleteMapping(value = DELETE_BOOK_BY_ID_MAPPING)
    public final BookResponse deleteBook(@RequestParam long bookId) {
        return bookService.deleteBook(bookId);
    }
}
