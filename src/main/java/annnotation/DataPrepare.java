package annnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPrepare {

    public enum Clazzs {
        Class, Method
    }
    // 设置默认值
    public Clazzs classType() default Clazzs.Method;

    String[] SheetForTable();

    String DataFile();


}
