package designpatterns;

/**
 * Created by liwenjing on 2017/11/2.
 */
public class Extender {
    public static void main(String[] args) {
        a1 aa=new aa1();
        aa.a1sqy();  //重写后将会引用子类的
    }
}

class a1{
    public void a1sqy(){
        System.out.println("im a1");
    }
}

class aa1 extends a1{
    public void aa1say(){
        System.out.println("im aa1");
    }

    @Override
    public void a1sqy(){
        System.out.println("im aa1 Override");
    }

}
