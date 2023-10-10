package pl.edu.pw.ee.library.api.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksControllerImpl {
    @GetMapping
    public String getBooks() {
        return "Hello world!";
    }
}
