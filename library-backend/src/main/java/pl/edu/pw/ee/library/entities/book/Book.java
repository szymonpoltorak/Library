package pl.edu.pw.ee.library.entities.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.pw.ee.library.entities.book.interfaces.BookUtil;

import static pl.edu.pw.ee.library.entities.book.constants.BookConstants.BOOKS_TABLE_NAME;

@Getter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = BOOKS_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Book implements BookUtil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    private String title;

    private String author;

    private int booksInStock;

    private int booksAvailable;

    @Override
    public final void returnBook() {
        if (booksAvailable >= booksInStock) {
            throw new IllegalStateException("All books were already returned");
        }
        booksAvailable++;
    }

    @Override
    public final void borrowBook() {
        if (booksAvailable <= 0) {
            throw new IllegalStateException("All books have been borrowed");
        }
        booksAvailable--;
    }
}
