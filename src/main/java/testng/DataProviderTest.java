package testng;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;

public class DataProviderTest {

    /**
     * 1.两种类型带入
     * 2.两种参数类型返回
     * 3.指定class运行
     */

    @DataProvider(name = "data1")
    public Object[][] data1(ITestContext context) {
        System.out.println(context.getName());
        return new Object[][] {
                {"lilei11", 20, "football"},
                {"hanmeimei", 18, "music"},
                {"wangnima", 25, "baozoumanhua"}
        };
    }

    @DataProvider(name = "dp")
    public Object[][] createData(Method m) {
        System.out.println(m.getName());  //打印method name
        return new Object[][] { new Object[] { "Cedric" }};
    }

    @Test(dataProvider = "data1"/*, dataProviderClass = StaticProvider.class*/)  //指class
    public void test(String name, int age, String hobby) {
        System.out.println(name + " is " + age + " years old and likes " + hobby);
    }

  /*  Data Provider方法可以返回如下两种类型中的一种：
     1.含有多个对象的数组 (Object[][])，其中第一个下标指明了测试方法要调用的次数，第二个下标则完全与测试方法中的参数类型和个数相匹配。上面的例子已经说明。
      2.另外一个是迭代器 Iterator<Object[]>。二者的区别是迭代器允许你延迟创建自己的测试数据。TestNG会调用迭代器，之后测试方法会一个接一个的调用由迭代器返回的值。在你需要传递很多参数组给测试组的时候，这样你无须提前创建一堆值。
*/


}
