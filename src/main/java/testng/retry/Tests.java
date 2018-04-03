package testng.retry;

import annnotation.DataPrepare;
import org.testng.*;
import org.testng.annotations.*;


/*@Listeners({MyListener.class,RetryListener.class})*/
/*@Listeners({MyListener.class})*/
@DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"},classType = DataPrepare.Clazzs.Class)
public class Tests {


    private  ITestContext myContext;

/*    @Test*//*(invocationCount = 2)*//*
    @Description(steps = "ss")
    @DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"})
    public void test(){
        System.out.println("-------");
    }*/


    @Test/*(invocationCount = 2)*/
    @DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"},classType = DataPrepare.Clazzs.Method)
    public void test1(){
        myContext.setAttribute("aaa","bbb");
        Assert.fail("-------");
    }

    @Test/*(invocationCount = 2)*/
    @DataPrepare(DataFile="ss.xls",SheetForTable={"inter_sheet","F_INTER"},classType = DataPrepare.Clazzs.Method)
    public void test2(){
        myContext.setAttribute("aaa","bbcccb");
        //Assert.fail("-------");
    }




    @BeforeMethod
    public void BeforeMethod(ITestResult testResult){

        System.out.println(this.getClass().getSimpleName());

      ;
    }

    @AfterMethod
    public void AfterMethod(ITestResult testResult,ITestContext testContext){
        System.out.println(testContext.getName());
        ITestResult it = Reporter.getCurrentTestResult();
        System.out.println(it.getName());
        System.out.println(testResult.getName());
        System.out.println(testResult.getTestContext().getAttribute("aaa"));
    }

  @BeforeClass
    public void BeforeClass(ITestContext testContext){
      parseType(this.getClass());
      myContext=testContext;

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


