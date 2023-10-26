package pl.edu.pw.ee.library.entities.book.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pw.ee.library.entities.book.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
}
