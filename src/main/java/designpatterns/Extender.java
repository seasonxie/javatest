package designpatterns;

/**
 * Created by liwenjing on 2017/11/2.
 */
public class Extender {

    public static void main(String[] args) {
        //子类对象转为父类对象    向上转型
        parent aa = new child();
        aa.a1sqy();      //1.重写后将会引用子类的
        // aa.aa1say();   //2.非重叠的子类方法遗失


        //父类对象转为子类父类对象转为子类对象   向下转型
        child bb = (child) aa;
        bb.a1sqy();
        bb.aa1say();

        //直接向下装报错
        parent aaa = new parent();
        //child bbb = (child) aaa;  //designpatterns.parent cannot be cast to designpatterns.child
    }
}

class parent {
    public void a1sqy() {
        System.out.println("im a1");
    }
}

class child extends parent {
    public void aa1say() {
        System.out.println("im aa1");
    }

    @Override
    public void a1sqy() {
        System.out.println("im aa1 Override");
    }

}
