package pl.edu.pw.ee.library.api.book.interfaces;

import pl.edu.pw.ee.library.api.book.data.BookRequest;
import pl.edu.pw.ee.library.api.book.data.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse addNewBook(BookRequest bookRequest);

    BookResponse getBookById(long bookId);

    List<BookResponse> searchByBookName(String bookName);

    BookResponse borrowBook(long bookId);

    BookResponse returnBook(long bookId);

    BookResponse deleteBook(long bookId);
}
