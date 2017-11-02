package testng;

import annnotation.DataPrepare;
import annnotation.Description;
import org.testng.*;

public class MyListener  implements IInvokedMethodListener, ITestListener {

    private boolean testSuccess = true;

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("beforeInvocation");
        if(method.isTestMethod() && annotationPresent(method, DataPrepare.class) ) {
            System.out.println("beforeAnnotation11111...");
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
            if( !testSuccess ) {
                testResult.setStatus(ITestResult.FAILURE);
            }
        }

    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {

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
            }
        }
    }
    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
