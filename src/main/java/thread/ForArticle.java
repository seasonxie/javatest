package thread;

/**
 * This code is explanation for article:
 */
public class ForArticle {

    public static void main(String[] args) {

//        demo2();
        demo3();

    }


    /**
     * A,B一起start, B 等待 A执行完再执行
     * A 1, A 2, A 3, B 1, B 2, B 3
     */
    private static void demo2() {
        final Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printNumber("B");
            }
        });

        A.start();
        B.start();
    }

    /**
     * A,B一起start, A打印1 后 A wait，B 执行完 通知A再执行
     * A 1, B 1, B 2, B 3, A 2, A 3
     */
    private static void demo3() {
        final Object lock = new Object();

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: A 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: A 得到了锁 lock");
                    System.out.println("A 1");
                    try {
                        System.out.println("INFO: A 准备进入等待状态，调用 lock.wait() 放弃锁 lock 的控制权");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("INFO: 有人唤醒了 A, A 重新获得锁 lock");
                    System.out.println("A 2");
                    System.out.println("A 3");
                }

            }
        });

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: B 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: B 得到了锁 lock");
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");

                    System.out.println("INFO: B 打印完毕，调用 lock.notify() 方法");
                    lock.notify();
                }
            }
        });

        A.start();
        B.start();
    }

    private static void printNumber(String threadName) {
        int j=0;
        while (j++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + j);
        }
    }

}
