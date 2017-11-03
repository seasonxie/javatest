package designpatterns;

import org.testng.annotations.Test;

public class Person extends SuperClass<Person>{
   /* public Person() {
        super();
    }*/



    public void function() {
        System.out.println("function in Person.class...");
        System.out.println(test);
    }

    @Test
    public void test1(){
        System.out.println("1111111");
        System.out.println(test);
    }
}