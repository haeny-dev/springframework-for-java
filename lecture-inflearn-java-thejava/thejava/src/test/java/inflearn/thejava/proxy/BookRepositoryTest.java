package inflearn.thejava.proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void di() {
        assertThat(bookRepository).isNotNull();

        Book book = new Book();
        book.setTitle("spring");
        bookRepository.save(book);
        bookRepository.findAll();
    }
}