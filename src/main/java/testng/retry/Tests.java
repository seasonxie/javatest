package testng.retry;

import ListMap.Lister;
import annnotation.DataPrepare;
import annnotation.Description;
import org.testng.*;
import org.testng.annotations.*;
import testng.MyListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/*@Listeners({MyListener.class,RetryListener.class})*/
@DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"},classType = DataPrepare.Clazzs.Class)
public class Tests {

/*    @Test*//*(invocationCount = 2)*//*
    @Description(steps = "ss")
    @DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"})
    public void test(){
        System.out.println("-------");
    }*/


    @Test/*(invocationCount = 2)*/
    @DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"},classType = DataPrepare.Clazzs.Method)
    public void test1(){
            Assert.fail("-------");
    }



   @BeforeMethod
    public void BeforeMethod(ITestResult testResult){
        System.out.println(this.getClass().getSimpleName());

      ;
    }
/*
    @AfterMethod
    public void AfterMethod(ITestResult testResult,ITestContext testContext){
        System.out.println(testContext.getName());
        ITestResult it = Reporter.getCurrentTestResult();
        System.out.println(it.getName());
        System.out.println(testResult.getName());
    }*/

  @BeforeClass
    public void BeforeClass(){
      parseType(this.getClass());
/*      System.out.println("BeforeClass");
     */
  }
    public static <T> void parseType(Class<T> clazz) {
        try {
            DataPrepare yts = ( DataPrepare) clazz.getAnnotation(DataPrepare.class);
            System.out.println(yts.classType().name() +" -------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


