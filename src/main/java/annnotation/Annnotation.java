package annnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * usr/local/git/bin/git pull

 * Created by zhaotang on 2017/10/18.
 */
public class Annnotation {

    public void onTestStart(){
		//ITestResult result
		// System.out.println(getDescription(result.getTestClass().getRealClass(),result.getName()));
    }

    public String getDescription(Class TestClass,String mname) {
        Description ds=this.getAnnotation(TestClass,Description.class,mname);
        return ds.steps();
    }

    public <T extends Annotation> T getAnnotation(Class<T> TestClass,Class<T> clazz,String mname) {
        try {
            if (TestClass.isAnnotationPresent(clazz)) {
                return TestClass.getAnnotation(clazz);
            }

            Method e = TestClass.getMethod(mname, new Class[0]);
            if (e.isAnnotationPresent(clazz)) {
                return e.getAnnotation(clazz);
            }
        } catch (NoSuchMethodException var3) {
            var3.printStackTrace();
        }

        return null;
    }
}
