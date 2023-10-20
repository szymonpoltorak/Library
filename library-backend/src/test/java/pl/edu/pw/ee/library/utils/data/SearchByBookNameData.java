package pl.edu.pw.ee.library.utils.data;

import pl.edu.pw.ee.library.api.book.data.BookResponse;
import pl.edu.pw.ee.library.entities.book.Book;

import java.util.List;

public record SearchByBookNameData(List<Book> bookList, List<BookResponse> bookResponseList) {
}
