package inflearn.thejava.reflection.classinfo;

import inflearn.thejava.reflection.annotation.AnotherAnnotation;
import inflearn.thejava.reflection.annotation.MyAnnotation;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

@MyAnnotation("Value")
public class Book {

    private static String B = "BOOK";
    private static final String C = "BOOK";

    private String a = "a";

    @AnotherAnnotation
    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    @AnotherAnnotation
    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    @AnotherAnnotation
    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }
}
