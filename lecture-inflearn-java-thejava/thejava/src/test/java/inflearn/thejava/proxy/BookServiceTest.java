package inflearn.thejava.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.mockito.Mockito.*;

class BookServiceTest {

    // Class 기반의 다이나믹 프록시는 생성할 수 없다.
    // Interface 기반의 다이나믹 프록시만 생성할 수 있다.
    /*
    BookService bookService = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(),
            new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("rent")) {
                        System.out.println("=== proxy: aaaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("=== proxy: bbbb");
                        return invoke;
                    }

                    return method.invoke(bookService, args);
                }
            });
     */

    @Test
    void rent() throws Exception {
        /* CGLIB 사용
        MethodInterceptor handler = new MethodInterceptor() {
            DefaultBookService bookService = new DefaultBookService();

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("=== preMethod");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("=== postMethod");
                    return invoke;
                }

                return method.invoke(bookService, args);
            }
        };
        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);
         */

        BookRepository bookRepositoryMock = mock(BookRepository.class);
        Book hibernateBook = new Book();
        hibernateBook.setTitle("Hibernate");
        when(bookRepositoryMock.save(any())).thenReturn(hibernateBook);

        /* ByteBuddy 사용 */
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    DefaultBookService bookService = new DefaultBookService(bookRepositoryMock);
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("rent")) {
                            System.out.println("=== preMethod");
                            Object invoke = method.invoke(bookService, args);
                            System.out.println("=== postMethod");
                            return invoke;
                        }

                        return method.invoke(bookService, args);
                    }
                }))
                .make().load(DefaultBookService.class.getClassLoader()).getLoaded();

        DefaultBookService bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

}