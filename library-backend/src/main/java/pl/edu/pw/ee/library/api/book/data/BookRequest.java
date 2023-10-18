package pl.edu.pw.ee.library.api.book.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookRequest(
        @NotNull(message = "title cannot be null") @NotBlank(message = "title cannot be blank") String title,
        @NotNull(message = "title cannot be null") @NotBlank(message = "title cannot be blank") String author,
        @Min(value = 1, message = "Amount of books in stock must be at least 1") int booksInStock) {
}
