package testng;

import annnotation.DataPrepare;
import annnotation.Description;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.*;
import testng.retry.TestngRetry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyListener extends TestListenerAdapter /*implements IInvokedMethodListener,ITestListener*/   {

    List<String> skipName=new ArrayList<>();
    List<String> classDataRemove=new ArrayList<>();
    List<String> methodDataRemove=new ArrayList<>();

 /*  @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("beforeInvocation");
        if(method.isTestMethod() && annotationPresent(method, DataPrepare.class) ) {
            System.out.println(testResult.toString());
        }

    }

    private boolean annotationPresent(IInvokedMethod method, Class clazz) {
        return method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(clazz) ? true : false;
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("afterInvocation");
        if(method.isTestMethod()) {
            if(method.getClass().isAnnotationPresent(Description.class)) {
                System.out.println("invoked afterAnnotation");
            }
          *//*  if( !testSuccess ) {
                testResult.setStatus(ITestResult.FAILURE);
            }
*//*
        }
    }*/


    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart");
        if(getAnnotation(iTestResult.getTestClass().getRealClass(),DataPrepare.class,iTestResult.getName())!=null)
        System.out.println( getAnnotation(iTestResult.getTestClass().getRealClass(),DataPrepare.class,iTestResult.getName()).classType().name()+" ======");

    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getTestContext().getAttribute("aaa")+" -----------------");
    }


    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println(tr.getName() + " Failure");
    }
    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        if(tr.getMethod().getRetryAnalyzer() !=null) {
            TestngRetry testRetryAnalyzer = (TestngRetry) tr.getMethod().getRetryAnalyzer();
            if(testRetryAnalyzer.getCount() <= testRetryAnalyzer.getMaxCount()) {
                skipName.add(tr.getName());
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart");
        for(ITestNGMethod m1 : context.getAllTestMethods()) {
            if(m1.getConstructorOrMethod().getMethod().isAnnotationPresent(Description.class)) {
                String name = m1.getConstructorOrMethod().getMethod().getAnnotation(Description.class).steps();
                Enum ss = m1.getConstructorOrMethod().getMethod().getAnnotation(Description.class).classType();
                System.out.println(ss.equals(Description.YtsType.service));
                System.out.println(ss.name());
            }
        }
    }
    @Override
    public void onFinish(ITestContext testContext) {
        System.out.println("onFinish");
        for (Iterator<ITestResult> iterator = testContext.getSkippedTests().getAllResults().iterator(); iterator.hasNext();) {
            ITestResult testResult = iterator.next();
            if(skipName.contains(testResult.getName())){
                System.out.println("remove");
                iterator.remove();
            }
        }
    }


    /**
     * 解析类注解
     * @param <T>
     * @param clazz
     */
    public static <T> void parseType(Class<T> clazz) {
        try {
            DataPrepare yts = ( DataPrepare) clazz.getAnnotation(DataPrepare.class);
            System.out.println(yts.classType().name() +" -------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends Annotation> T getAnnotation(Class<T> TestClass, Class<T> clazz, String mname) {
        try {
          /*  if (TestClass.isAnnotationPresent(clazz)) {
                return TestClass.getAnnotation(clazz);
            }
*/
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
