package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Threads {
    //需要继承
    public class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }

        public void main(String[] args) {
            MyThread myThread1 = new MyThread();
            myThread1.run();
        }
    }

    //实现接口即可
    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }

        public void main(String[] args) {
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();
        }
    }


    //Callable的任务执行后可返回值，而Runnable的任务是不能返回值得
    //call方法可以抛出异常，run方法不可以
    public class MyCallable<V> implements Callable<V> {
        @Override
        public V call() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }

        public void main(String[] args) {
            Callable<V> oneCallable = new MyCallable<V>();
            //由Callable<Integer>创建一个FutureTask<Integer>对象：
            FutureTask<V> oneTask = new FutureTask<V>(oneCallable);
            //注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
            //由FutureTask<Integer>创建一个Thread对象：
            Thread oneThread = new Thread(oneTask);
            oneThread.start();
        }
        //Future对象，Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。计算完成后只能使用 get 方法来获取结果

    }
}
