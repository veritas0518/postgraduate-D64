package Day31;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadPool
 * @Description: 创建线程的方式四：使用线程池
 * @Author: TianXing.Xue
 * @Date: 2021/7/27 14:30
 * @Version: 1.0
 * <p>
 *   好处：
 *   1.提高相应速度(减少了创建新线程的时间)
 *   2.降低资源消耗(重复利用线程池中线程，不需要每次都创建)
 *   3.便于线程管理
 *           corePoolSize:核心池的大小
 *           maximumPoolSize:最大线程数
 *           keepAliveTime:线程没有任务时最多保持多长时间会终止
 *
 *   面试题：创建多线程有几种方式？
 *   答：四种
 *   继承Thread类
 *   实现Runnable接口
 *   实现Callable接口
 *   线程池（响应速度提高了，提高了资源的重用率，便于管理）
 **/
class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //1.提供指定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //因为ExecutorService是一个接口，里面是常数，所以不可能调用它，经过寻找是ThreadPoolExecutor
        ThreadPoolExecutor services1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
        services1.setCorePoolSize(15);
        //services1.setKeepAliveTime();
        //2.执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread1());  //适合使用于Runnable
        service.execute(new NumberThread2());
        //service.submit(Callable callable);  //适合使用于Callable
        //3.关闭连接池
        service.shutdown();
    }
}
