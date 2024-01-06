package inflearn.thejava.reflection.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Inherited
public @interface AnotherAnnotation {

    String value() default "haeny";
    int number() default 100;
}
