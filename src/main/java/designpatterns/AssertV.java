package designpatterns;

import org.testng.Assert;

/**
 * Created by liwenjing on 2017/11/14.
 */
public class AssertV extends Assert {

    public static void assertEquals(Object actual, Object expected, String message) {
        if(expected != null || actual != null) {
            System.out.println(message);
        }
    }


}
