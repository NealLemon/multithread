package base.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Neal
 * 需要检查的线程
 */
public class CheckThread implements Runnable{

    private CountDownLatch countDownLatch;

    public CheckThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(" check complete");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();  //通知CountDownLatch 已完成一个线程
    }
}
