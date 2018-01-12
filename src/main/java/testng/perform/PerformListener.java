package testng.perform;

import annnotation.DataPrepare;
import annnotation.Description;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import testng.model.PerformAnnotation;
import testng.model.PerformInfo;
import testng.retry.TestngRetry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PerformListener extends TestListenerAdapter {

    public static List<PerformInfo> pi=new ArrayList<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart");
        System.out.println(iTestResult.getName());
        System.out.println(getAnnotation(iTestResult.getTestClass().getRealClass(),PerformAnnotation.class,iTestResult.getName()));;
        if(getAnnotation(iTestResult.getTestClass().getRealClass(),PerformAnnotation.class,iTestResult.getName())!=null){
            String tn = iTestResult.getTestContext().getCurrentXmlTest().getParameter("testCase");
            PerformInfo info = new PerformInfo();
            info.setName(tn);
            info.setClassName(iTestResult.getInstanceName());
            info.setMethodName(iTestResult.getName());
            info.setDescription(iTestResult.getMethod().getDescription());
            if(pi.size()>0){
                info.set_90thPercentile("25");
                info.setAvgLatency("23");
                info.setBytesPerRequestAvg("98.0 B");
                info.setError("0");
                info.setRequests("1001");
                info.setMinLatency("21");
                info.setMaxLatency("29");
                info.setThroughput("600.479");
            }else{
                info.set_90thPercentile("27");
                info.setAvgLatency("24");
                info.setBytesPerRequestAvg("68.0 B");
                info.setError("0");
                info.setRequests("2001");
                info.setMinLatency("23");
                info.setMaxLatency("37");
                info.setThroughput("660.479");
            }
            pi.add(info);
        }
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }


    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println(tr.getName() + " Failure");
    }
    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart");

    }
    @Override
    public void onFinish(ITestContext testContext) {
        System.out.println("onFinish");

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
