package spring;

public class spring {
    public
    static  void  main(String args[]) {

        Thread t = new
                Thread() {

                    @Override
                    public
                    void  run() {

                        pong();

                    }

                };

        // t.run();//如果是这行  则输出结果为：

        t.start();//如果是这行  则输出结果为：pongping

        System.out.print("ping");

    }

    static
    void  pong() {

        System.out.print("pong");

    }


}
