package used;

public class classT {
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


}
