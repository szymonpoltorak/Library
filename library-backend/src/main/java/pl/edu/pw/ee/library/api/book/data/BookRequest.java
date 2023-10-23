package pl.edu.pw.ee.library.api.book.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.AUTHOR_NOT_BLANK_MESSAGE;
import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.AUTHOR_NOT_NULL_MESSAGE;
import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.BOOKS_IN_STOCK_MESSAGE;
import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.MINIMUM_AMOUNT_OF_BOOKS;
import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.TITLE_NOT_BLANK_MESSAGE;
import static pl.edu.pw.ee.library.api.book.data.constants.RecordConstants.TITLE_NOT_NULL_MESSAGE;

@Builder
public record BookRequest(
        @NotNull(message = TITLE_NOT_NULL_MESSAGE) @NotBlank(message = TITLE_NOT_BLANK_MESSAGE) String title,
        @NotNull(message = AUTHOR_NOT_NULL_MESSAGE) @NotBlank(message = AUTHOR_NOT_BLANK_MESSAGE) String author,
        @Min(value = MINIMUM_AMOUNT_OF_BOOKS, message = BOOKS_IN_STOCK_MESSAGE) int booksInStock) {
}
