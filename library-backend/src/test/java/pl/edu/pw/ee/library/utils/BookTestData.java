package pl.edu.pw.ee.library.utils;

import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;

public record BookTestData(Book book, BookResponse bookResponse) {
}
