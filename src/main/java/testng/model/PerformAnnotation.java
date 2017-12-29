package testng.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformAnnotation {

    String url();
    String body() default "";
    ReqMethod method();
    String content_type() default "";
    String cookie() default "";
}
