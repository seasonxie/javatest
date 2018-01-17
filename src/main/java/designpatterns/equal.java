package designpatterns;


/**
 * Created by zhaotang on 2017/10/19.
 */
public class equal {

    public static void main(String[] args) {
        "ss".equals("");

    }



    class A {
        // static{} > { }  >  构造器


        private String a = "a";  //堆，如果"a"再string池中不存在，就会新建一个new string（"a"）对象
        //存对象，堆区被所有线程共享，存放对象本身

        public void test1() {
            String b = "b";
            //每线程都有一个栈，保存的是对象的引用，其他栈不能访问
        }

        //方法区/静态区，被所有线程共享 class，static
    }

    public void test2() {
        int i = 0;
        System.out.println(i++); //0
        System.out.println(++i); //2
        int j = 0;
        j = j++ + j++;
        System.out.println(j);//1
        j = 0;
        j = j++ + ++j;
        System.out.println(j);//2

        //&（两边boolean类型） 无论前面true 或者 false都会执行后面
        //&（两边int类型） 把两边转为2进制后再针对每个位置（个十百千）运算
        //&& 和上面相fang
    }


    public void test1() {
        String str = "hello";  //盏
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
