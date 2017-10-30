package testng;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({MyListener.class})
public class TestListener {

    @Test
    public void test(){
        System.out.println("---");
    }

    @BeforeClass
    public void beforeClass(ITestContext iTestContext){
        System.out.println(iTestContext==null);
        System.out.println(iTestContext.getName());
    }
}
