package base.printABC.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA implements Runnable {
    //原子性 integer对象
    private AtomicInteger count;

    //监听器
    private Object s;

    //构造函数
    public ThreadA(AtomicInteger count, Object s) {
        this.count = count;
        this.s = s;
    }

    public void run() {
        while(true) {
            if (count.get() == 1) {
                synchronized (s) {
                    System.out.println("A");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.set(2);
                    s.notifyAll();
                }
            }
        }


    }
}
