package base.printABC.relock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadC implements Runnable {
    //原子性 integer对象
    private AtomicInteger count;

    //监听器
    //监听器
    private ReentrantLock lock;

    //构造函数
    public ThreadC(AtomicInteger count, ReentrantLock lock) {
        this.count = count;
        this.lock = lock;
    }

    public void run() {
        while(true) {
            if (count.get() == 3) {
                lock.lock();
                    System.out.println("C");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.set(1);
                lock.unlock();
                }
            }
        }


    }

