package testng;

import annnotation.Description;
import org.testng.*;

public class MyListener  implements IInvokedMethodListener, ITestListener {

    private boolean testSuccess = true;

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("beforeInvocation");
        if(method.isTestMethod() && annotationPresent(method, Description.class) ) {
            System.out.println("beforeAnnotation...");
            System.out.println(testResult.toString());
        }

    }

    private boolean annotationPresent(IInvokedMethod method, Class<Description> clazz) {
        return method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(clazz) ? true : false;
    }

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



    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext context) {
        System.out.println("onStart");
        for(ITestNGMethod m1 : context.getAllTestMethods()) {
            if(m1.getConstructorOrMethod().getMethod().isAnnotationPresent(Description.class)) {
                String name = m1.getConstructorOrMethod().getMethod().getAnnotation(Description.class).steps();
            }
        }
    }

    public void onFinish(ITestContext iTestContext) {

    }
}
