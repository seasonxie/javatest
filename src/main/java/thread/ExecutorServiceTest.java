package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zhaotang on 2017/10/18.
 */
public class ExecutorServiceTest {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(500);
    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程

    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    //创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：

    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下

    public void test(){
        fixedThreadPool.submit( new Runnable() {
            @Override
            public void run() {
                System.out.println("test1");
            }
        } );
    }

    public static void main(String[] args) {
        ExecutorServiceTest et=new ExecutorServiceTest();
        et.test();
    }
}
