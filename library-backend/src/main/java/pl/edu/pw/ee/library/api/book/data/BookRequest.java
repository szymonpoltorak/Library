package pl.edu.pw.ee.library.api.book.data;

import lombok.Builder;

@Builder
public record BookRequest(String title, String author, int booksInStock) {
}
