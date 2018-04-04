package designpatterns.proxy;

public class run extends abrust {
    @Override
    public void close() {
        System.out.println("close");
    }

    @Override
    public void open2() {
        System.out.println("open2");
    }

    public static void main(String[] args) {
        System.out.println(sss);

        run i=new run();
        System.out.println(i.ss);
        i.open1();
        i.open2();
        i.open3();


    }
}
