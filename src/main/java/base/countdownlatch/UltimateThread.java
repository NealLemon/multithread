package base.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 最终的线程
 */
public class UltimateThread implements Runnable{

    private CountDownLatch countDownLatch;

    public UltimateThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();  //等待CountDownLatch 其他线程完成后才开始执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ALL threads is complete!");
    }
}
