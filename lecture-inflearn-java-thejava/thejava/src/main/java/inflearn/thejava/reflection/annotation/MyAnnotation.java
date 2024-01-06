package inflearn.thejava.reflection.annotation;

import org.springframework.lang.Nullable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 기본값은 CLASS
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    String value() default "haeny";     // value 는 value 생략가능
    int number() default 100;
}
