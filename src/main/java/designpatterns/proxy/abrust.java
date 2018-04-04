package designpatterns.proxy;

public abstract class abrust implements interfa{
    public  String ss="ssssss";

       @Override
       public void open(){
           System.out.println("abstract open");
       }

    public void open1(){
        System.out.println("abstract open1");
    }

    public abstract void open2();

    public void open3(){
        System.out.println("abstract open3");
    }


}
