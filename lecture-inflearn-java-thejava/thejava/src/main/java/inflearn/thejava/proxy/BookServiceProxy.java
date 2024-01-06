package inflearn.thejava.proxy;

public class BookServiceProxy implements BookService{

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("BookServiceProxy.rent");
        book.setTitle("Hibernate");
        bookService.rent(book);
        System.out.println("bbbb");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("BookServiceProxy.returnBook");
        bookService.returnBook(book);
    }
}
