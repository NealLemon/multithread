package base.printABC.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Neal
 * 解答要点: 1.首先使用了带有原子性的 AtomicInteger 对象，保证并发的原子性
 *           2.利用synchronized关键字来为每个线程监听加锁，防止在休眠的时候，其他线程进行对AtomicInteger赋值操作
 */
public class Main {
    public static void main(String[] args) {
        Object s = new Object();  //监听器对象
        AtomicInteger integer = new AtomicInteger(1);
        ThreadA a = new ThreadA(integer,s);
        ThreadB b = new ThreadB(integer,s);
        ThreadC c = new ThreadC(integer,s);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(a);
        executorService.execute(b);
        executorService.execute(c);

    }
}
