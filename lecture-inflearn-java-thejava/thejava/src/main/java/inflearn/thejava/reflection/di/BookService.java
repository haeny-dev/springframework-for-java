package inflearn.thejava.reflection.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
}
