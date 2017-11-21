package designpatterns.fx;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.ParameterizedType;

public class SuperClass<T> {

/*
    List<String> —- 参数化的类型
    List<E> —- 泛型
    List<?> —- 无限制通配符类型
            <E extends SomeClass> —- 有限制类型参数
    List <? extends SomeClass>—- 有限制通配符类型
            <T extends Comparable<T>> —– 递归类型限制
    static <E> List<E> asList(E[] a) —- 泛型方法
    泛型三种：
            [1]ArrayList<T> al=new ArrayList<T>();指定集合元素只能是T类型
          [2]ArrayList<?> al=new ArrayList<?>();集合元素可以是任意类型，这种没有意义，一般是方法中，只是为了说明用法
          [3]ArrayList<? extends E> al=new ArrayList<? extends E>();
    泛型的限定：
            ? extends E:接收E类型或者E的子类型。
            ？super E:接收E类型或者E的父类型。
            */

    private Class<T> clazz;
    public String test="ss";

    @SuppressWarnings("unchecked")
    public SuperClass() {

        System.out.println("SuperClass");
        clazz = (Class<T>) ((ParameterizedType) super.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(getClazz());
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void testOverride(){
        System.out.println("-----");
    }

    /**
     * 普通的非泛型类Class
     * 泛型化的类Class<T>
     * JDK中，普通的Class.newInstance()方法的定义返回Object，要将该返回类型强制转换为另一种类型;
     * 但是使用泛型的Class<T>，Class.newInstance()方法具有一个特定的返回类型;
     *
     * @param args
     */
    @BeforeMethod
    public void beforeMethod() throws IllegalAccessException, InstantiationException {
        System.out.println("beforeMethod");
        testOverride();
        Person s=(Person)clazz.newInstance();
        s.test1="11111111sss";
        s.function();
      /*  //SuperClass<Person> superClass = new SubClass();
        SuperClass<Person> superClass = new Person();
        //1.得到泛型类T实际的完整类名
        System.out.println(superClass.getClazz());
        //2.得到泛型类T的对象
        try {
            System.out.println(superClass.getClazz().newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //3.调用泛型类T的方法
        try {
            Person s=superClass.getClazz().newInstance();
            s.function();
            s.test="111";
            s.function();
            test="22";
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
 /*   public static void main(String[] args) {
        //SuperClass<Person> superClass = new SubClass();
        SuperClass<Person> superClass = new Person();
        //1.得到泛型类T实际的完整类名
        System.out.println(superClass.getClazz());
        //2.得到泛型类T的对象
        try {
            System.out.println(superClass.getClazz().newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //3.调用泛型类T的方法
        try {
            Person s=superClass.getClazz().newInstance();
            s.function();
            s.test="111";
            s.function();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/

}
class SubClass extends SuperClass<Person> {

    public SubClass() {
        super();
    }

}


