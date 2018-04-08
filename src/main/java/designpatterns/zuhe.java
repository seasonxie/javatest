package designpatterns;

import org.testng.annotations.Test;

public  class zuhe{
    @Test
    public void runner(){
        zuhe zh=new zuhe();
        zh.new b("ss1").test();
        zh.new c("sss2");
    }

abstract class  a{
        String ss;
    a(String ss){
        this.ss=ss;
    }
    abstract void test();

    void test1(){
        System.out.println(ss);
    }
}

class b extends a{

    b(String ss) {
        super(ss);
    }

    @Override
    void test() {
     //   System.out.println("11");
        test1();
    }
}

class c{
       c(String ss){
           System.out.println(ss);
       }
    }


}
