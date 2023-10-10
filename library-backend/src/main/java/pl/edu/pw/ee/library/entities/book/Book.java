package pl.edu.pw.ee.library.entities.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static pl.edu.pw.ee.library.entities.book.constants.BookConstants.BOOKS_TABLE_NAME;

@Data
@Builder
@Entity
@Table(name = BOOKS_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    private String title;

    private String author;

    private int booksInStock;

    private int booksAvailable;
}
