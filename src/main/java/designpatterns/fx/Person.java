package designpatterns.fx;

import org.testng.annotations.Test;

public class Person/*extends SuperClass<Person>*/{
    public Person() {

        super();
        System.out.println("superPerson");
    }

 /*   @Override
    public void testOverride(){
        System.out.println("cccccccccc");
    }*/

    public String test1="ss1";

    public void function() {
        System.out.println("function in Person.class...");
        System.out.println(test1);
    }

    public static void main(String[] args) {
        System.out.println("1111111");
        //System.out.println(test1);
    }

    @Test
    public void test1(){
        System.out.println("1111111");
        System.out.println(test1);
    }
}