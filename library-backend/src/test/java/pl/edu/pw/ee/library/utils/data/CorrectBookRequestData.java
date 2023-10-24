package pl.edu.pw.ee.library.utils.data;

import pl.edu.pw.ee.library.api.book.data.BookRequest;

import java.util.stream.Stream;

public record CorrectBookRequestData(Stream<BookRequest> correctBookRequestData) {
}
