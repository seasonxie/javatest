package testng;

import org.testng.annotations.Test;

public class baseann {

    @Test(threadPoolSize = 2, invocationCount = 10, timeOut = 1000)

    public void testServer() throws InterruptedException {
        // 检测启动的线程数,当启动的个数超过CPU核数时,其实是重新在调度
        // Thread.sleep(2000);
        int sum=0;
        sum++;
        System.out.println("........." + sum);
    }

   /* @Test(enabled = false)  忽略测试
    @Test(groups = { "checkintest" })  组件测试
    @Test(expectedExceptions = ArithmeticException.class)  期待异常
    @Test(dependsOnMethods = { "initEnvironmentTest" })  依赖测试*/
}
