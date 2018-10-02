package base.printABC.relock_condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Neal
 * 解答要点: 1.首先使用了带有原子性的 AtomicInteger 对象，保证并发的原子性
 *           2.利用一个ReentrantLock 生成3个条件标志(condition)来为每个线程监听加锁，防止在休眠的时候，其他线程进行对AtomicInteger赋值操作
 */
public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();  //可重入锁
        Condition ca = lock.newCondition();  //A条件
        Condition cb = lock.newCondition();  //B条件
        Condition cc = lock.newCondition();  //C条件
        AtomicInteger integer = new AtomicInteger(1);
        ThreadA a = new ThreadA(integer,lock,cb,ca);
        ThreadB b = new ThreadB(integer,lock,cc,cb);
        ThreadC c = new ThreadC(integer,lock,ca,cc);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(a);
        executorService.execute(b);
        executorService.execute(c);
        executorService.shutdown();

    }
}
