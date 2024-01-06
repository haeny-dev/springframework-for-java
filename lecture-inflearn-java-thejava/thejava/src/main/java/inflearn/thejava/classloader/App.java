package inflearn.thejava.classloader;

public class App {

    private Book book = new Book();

    public static void main(String[] args) {

        // 클래스 로더의 로딩이 끝나면 해당 클래스 타입의 Class 객체를 Heap 영역에 저장
        Class<? extends Class> bookClass = Book.class.getClass();

        // 해당 클래스의 클래스 로더를 확인할 수 있고, 그 상속관계 또한 확인할 수 있다.
        ClassLoader classLoader = App.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);
        System.out.println("classLoader.getParent() = " + classLoader.getParent());
        System.out.println("classLoader.getParent().getParent() = " + classLoader.getParent().getParent());
    }
}
