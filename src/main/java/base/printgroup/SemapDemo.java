package base.printgroup;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Neal
 * 解答要点: 主要考察 信号量(Semaphore) 的使用, 信号量可以指定多个线程同时访问。
 */
public class SemapDemo implements Runnable {

    private Semaphore semaphore;

    public SemapDemo(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try{
            semaphore.acquire(); //获取资源
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("Hello World");
            semaphore.release(); //释放资源
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
