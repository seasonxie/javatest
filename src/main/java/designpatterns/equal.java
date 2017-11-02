package designpatterns;


/**
 * Created by zhaotang on 2017/10/19.
 */
public class equal {

    public static void main(String[] args) {


    }

    //抽象类和接口


    //Array和Linked

     class A  {
        // static{} > { }  >  构造器
        private String a="a";
        //存对象，堆区被所有线程共享，存放对象本身
         public void test1() {
             String b="b";
             //每线程都有一个栈，保存的是对象的引用，其他栈不能访问
         }
          //方法区/静态区，被所有线程共享 class，static
    }

    public void test2(){
        int i=0;
        System.out.println(i++); //0
        System.out.println(++i); //2
        int j=0;
        j=j++ + j++;
        System.out.println(j);//1
        j=0;
        j=j++ + ++j;
        System.out.println(j);//2

        //& 无论前面true 或者 false都会执行后面
        //&& 和上面相fang
    }


    public void test1() {
        String str = "hello";
        String s1 = str;
        str = null;
        System.out.println(s1); //hello

        String newstr = new String("hello");
        String s2 = str;
        newstr = null;
        System.out.println(s2); // null

        // int  基本类型
        // Integer 对象：包装类
        //每个基本类型都有包装类，可以传入字符串构造int
        int i=1;
        Integer ii=new Integer("1");
        System.out.println(i==ii); //true
    }

    public void Arraylist() {
   /* public E remove(int index);
    public boolean remove(Object o);
    当我们传入int类型的时候，会自动被当成上面那个方法来调用。而下面那个移除某个特定元素的方法是需要传入一个Object对象*/
    }
}
