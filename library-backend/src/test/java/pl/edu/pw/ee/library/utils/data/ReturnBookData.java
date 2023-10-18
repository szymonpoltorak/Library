package pl.edu.pw.ee.library.utils.data;

import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;

public record ReturnBookData(long bookId, Book preReturn, Book postReturn, BookResponse bookResponse) {
}
