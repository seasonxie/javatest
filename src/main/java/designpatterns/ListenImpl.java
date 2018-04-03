package designpatterns;

public class ListenImpl {
    public static void main(String[] args) {
        new listenDo(new listenI() {
            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onEnd() {
                System.out.println("onEnd");
            }
        }).mains().start();
    }
}

class listenDo {
    listenI li;

    listenDo(listenI li) {
        this.li = li;
    }

    public listenDo mains() {
        li.onStart();
        System.out.println("----------");
        li.onEnd();
        return this;
    }

    public void start(){
        System.out.println("start");
    }

}
interface listenI{
    public void onStart();
    public void onEnd();
}
