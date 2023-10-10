package pl.edu.pw.ee.library.entities.book.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.ee.library.entities.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
