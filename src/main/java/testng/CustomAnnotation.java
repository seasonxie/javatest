package testng;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CustomAnnotation implements IAnnotationTransformer {


    @SuppressWarnings("rawtypes")
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        System.out.println(" MyTransformer  "+testMethod);
        if ("test1".equals(testMethod.getName())) {
            annotation.setInvocationCount(5);
        }

    }

}
