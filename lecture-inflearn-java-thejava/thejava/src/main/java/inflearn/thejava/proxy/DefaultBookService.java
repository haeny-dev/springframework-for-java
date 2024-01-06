package inflearn.thejava.proxy;


public class DefaultBookService implements BookService{

    BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("return: " + bookRepository.save(book).getTitle());
    }
}
