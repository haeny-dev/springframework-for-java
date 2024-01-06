package inflearn.thejava.reflection.classinfo;


import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;
/*
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        Class<?> aClass1 = Class.forName("inflearn.thejava.reflection.classinfo.Book");
*/

//        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        Book book = new Book();

        System.out.println("=== DeclaredFields === ");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s -> %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println("=== Methods === ");
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        System.out.println();

        System.out.println("=== Constructors === ");
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);
        System.out.println();

        System.out.println("=== Superclass === ");
        System.out.println(MyBook.class.getSuperclass());
        System.out.println();

        System.out.println("=== Interface === ");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
        System.out.println();

        System.out.println("=== Modifiers === ");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
        });
    }
}
