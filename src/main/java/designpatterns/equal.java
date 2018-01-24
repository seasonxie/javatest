package designpatterns;


import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by zhaotang on 2017/10/19.
 */
public class equal {

    public static void main(String[] args) {
        ArrayList ss=new ArrayList();
        LinkedList aa=new LinkedList();
        HashMap dd=new HashMap<>();
        Set sd=new HashSet();

    }

    @Test
    public void test2() {
        int i = 0;
        System.out.println(i++); //0
        System.out.println(++i); //2
        int j = 0;
        j = j++ + j++;
        System.out.println(j);//1
        j = 0;
        //    0 + ++1(2)=2
        j = j++ + ++j;
        System.out.println(j);//2

        j=0;
        //    1 + ++1(2)=3
        j =  ++j + ++j;
        System.out.println(j);//3
         //一个返回+之后的值一个返回+之前的值

        //&（两边boolean类型） 无论前面true 或者 false都会执行后面
        //&（两边int类型） 把两边转为2进制后再针对每个位置（个十百千）运算
        //&& 和上面相fang
    }



    class A {

        // static{} > { }  >  构造器（先父类后子类）
        //静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化，并且只会执行一次

        private String a = "a";  //方法区常量池 ，String池中开辟一块空间，存放String常量"abc"，
        //存对象，堆区被所有线程共享，存放对象本身

        public void test1() {
            //equal是Object方法 string继承实现了内容。。如果没有实现就是 ==
            //每线程都有一个栈，保存的是对象的引用，其他栈不能访问
            String s1 = "a"; //方法区常量池
            String s2 = "b";//方法区常量池
            String s5=new String("a");//dui
            System.out.println(s1 == "a");  //true
            System.out.println(s1 == s5);  //false
            String str6 = s1 + s2; //dui
            System.out.println(str6 == "ab");  //false

            String str4 = "a" + "b";//changliangci
            System.out.println(str4 == "ab");  //true

            final String s = "a";//changliangci
            String str5 = s + "b";
            System.out.println(str5 == "ab");//true

            String c = "a";
            String strc = c + "b"; //dui
            System.out.println(strc == "ab");//false
        }
        //方法区/静态区，被所有线程共享 class，static
    }


    public void test1() {
        String str = "hello";  //
        String s1 = str;
        str = null;
        System.out.println(s1); //hello

        String newstr = new String("hello");
        String s2 = str;
        newstr = null;
        System.out.println(s2); // null

       /* int a=18  //JVM栈
        public void add(int a){
            a=2 //方法栈
        }  //对add的局部变量a的值修改，不影响主函数中的a
        ---------  a=18

        如果是对象，就是对引用地址的拷贝。
        color r=new color()
        r.set(red)
        public void add(color r){
            color r2=new color(blue)
            r=r2  //r为add的局部变量保存的是传入的引用地址
            r.set(black)
        }
        --------- r.getColor=red*/

        // int  基本类型
        // Integer 对象：包装类
        //每个基本类型都有包装类，可以传入字符串构造int.Integer会自动拆箱为int，所以为true
        int i = 1;
        Integer ii = new Integer("1");
        System.out.println(i == ii); //true
    }

    public void Arraylist() {
   /* public E remove(int index);
    public boolean remove(Object o);
    当我们传入int类型的时候，会自动被当成上面那个方法来调用。而下面那个移除某个特定元素的方法是需要传入一个Object对象*/
    }

    public void Reference() {
        //StrongReference  宁愿outofmemery也不回收
        //SoftReference 内存不足回收
        //WeakReference 扫描到就回收
        //PhantomReference 任何时候都可能被
    }


}
