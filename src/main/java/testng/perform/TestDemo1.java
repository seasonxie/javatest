package testng.perform;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testng.model.PerformAnnotation;
import testng.model.ReqMethod;


@Listeners({ZTestReport.class,PerformListener.class})
public class TestDemo1{


	@Test(description="测试testPerformance")
	@PerformAnnotation(url = "www.meizu.com", method = ReqMethod.GET)
	public void testPerformance(){
		Reporter.log("测试testPerformance!");

	}

	@Test(description="测试testPerformance")
	@PerformAnnotation(url = "www.meizu.com", method = ReqMethod.GET)
	public void testPerformance1(){
		Reporter.log("测试testPerformance!");

	}

	@Test(description="测试DEMO3")
	public void testDemo3(){
		Reporter.log("this is demo!");
		Assert.assertEquals("a", "a", "should be equals.");
	}

	/*@BeforeMethod(description="测试方法前初始化")
	public void beforeMethod(Method m){
		if("testDemo3".equals(m.getName())){
			int a = 1/0;
			System.out.println(a);
		}
	}
	
	@Test(description="测试DEMO")
	public void testDemo(){
		Reporter.log("this is demo!");
		int a = 1/0;
		System.out.println(a);
		Assert.assertEquals("a", "b", "should be equals.");
	} 
	
	@Test(description="测试DEMO1")
	public void testDemo1(){
		Reporter.log("this is demo!");
		Assert.assertEquals("a", "b", "should be equals.");
	} 
	
	@Test(description="测试DEMO2",dataProvider="test")
	public void testDemo2(int a){
		Reporter.log("this is demo!");
		Assert.assertEquals(a, 1, "should be equals.");
	} 
	

	
	@DataProvider(name="test")
	public Object[][] dataProvider(){
		return new Object[][]{{1},{2}};
	}*/
	
}
