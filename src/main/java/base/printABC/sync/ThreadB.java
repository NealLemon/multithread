package base.printABC.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadB implements Runnable {
    //原子性 integer对象
    private AtomicInteger count;

    //监听器
    private Object s;



    //构造函数

    public ThreadB(AtomicInteger count, Object s) {
        this.count = count;
        this.s = s;
    }

    public void run() {
        while(true) {
            if (count.get() == 2) {
                synchronized (s) {
                    System.out.println("B");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.set(3);
                    s.notifyAll();
                }
            }
        }


    }
}
