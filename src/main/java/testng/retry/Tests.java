package testng.retry;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.MyListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class Tests {

    @Test(invocationCount = 2)
    public void test(){
        System.out.println("-------");
    }


}


