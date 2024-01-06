package inflearn.thejava.reflection.annotation;

import inflearn.thejava.reflection.classinfo.Book;
import inflearn.thejava.reflection.classinfo.MyBook;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
//        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
//        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof AnotherAnnotation) {
                    AnotherAnnotation annotation = (AnotherAnnotation) a;
                    System.out.println("annotation.value() = " + annotation.value());
                    System.out.println("annotation.number() = " + annotation.number());
                }
            });
        });

        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            Arrays.stream(m.getAnnotations()).forEach(System.out::println);
        });
    }
}
