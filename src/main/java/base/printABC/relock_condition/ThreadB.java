package base.printABC.relock_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadB implements Runnable {
    //原子性 integer对象
    private AtomicInteger count;

    private ReentrantLock lock;

    private Condition next;

    private Condition self;

    //构造函数
    public ThreadB(AtomicInteger count, ReentrantLock lock, Condition next, Condition self) {
        this.count = count;
        this.lock = lock;
        this.next = next;
        this.self = self;
    }

    public void run() {
        try {
            lock.lock();
            while(true) {
                if (count.get() != 2)
                    self.await();
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("B");
                count.set(3);
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        }


    }

