package testng.retry;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.CustomAnnotation;

@Listeners(CustomAnnotation.class)
public class Test1 {

    @Test
    public void test11(){
        System.out.println("11");
    }
}
