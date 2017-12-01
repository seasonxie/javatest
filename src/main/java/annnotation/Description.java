package annnotation;

import ListMap.Lister;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhaotang on 2017/10/10.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface Description {

/*    @Description(steps = "sss")*/

    String steps();
    public enum YtsType {
        util, entity, service, model
    }
    // 设置默认值
    public YtsType classType() default YtsType.util;
}
