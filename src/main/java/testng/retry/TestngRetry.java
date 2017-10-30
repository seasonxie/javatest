package testng.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class TestngRetry implements IRetryAnalyzer {

    private static final String TEST_RETRY_COUNT = "testRetryCount";
    private int count = 1;
    private int maxCount = 1;

    public TestngRetry() {
        String retryMaxCount = System.getProperty(TEST_RETRY_COUNT);
        if (retryMaxCount != null) {
            maxCount = Integer.parseInt(retryMaxCount);
        }
    }

    public int getCount() {
        return this.count;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public synchronized boolean retry(ITestResult result) {
        String testClassName = String.format("%s.%s", result.getMethod()
                .getRealClass().toString(), result.getMethod().getMethodName());
        if (count <= maxCount) {
            result.setAttribute("RETRY", new Integer(count));
            count += 1;
            return true;
        }
        return false;
    }
}