package inflearn.thejava.reflection.classinfoedit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("inflearn.thejava.reflection.classinfoedit.Book");
        Constructor<?> constructor = bookClass.getConstructor(null);
        Book book = (Book) constructor.newInstance();

        Constructor<?> constructor1 = bookClass.getConstructor(String.class);
        Book myBook = (Book) constructor1.newInstance("myBook");

        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null));
        
        a.set(null, "AAAAA");
        System.out.println("a.get(null) = " + a.get(null));

        Field b = Book.class.getDeclaredField("B");
        b.setAccessible(true);
        System.out.println("b.get(book) = " + b.get(book));

        b.set(book, "BBBBB");
        System.out.println("b.get(book) = " + b.get(book));

        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(book);

        Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int result = (int) sum.invoke(book, 1, 2);
        System.out.println("result = " + result);
    }
}
