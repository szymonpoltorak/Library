package pl.edu.pw.ee.library.api.book.data;

import lombok.Builder;

@Builder
public record BookResponse(String title, String author, long bookId,
                           int booksInStock, int booksAvailable) {
}
