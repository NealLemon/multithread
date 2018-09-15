package base.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Neal
 * 主要练习 CountDownLatch 多线程控制工具类
 *  这个类通常来控制线程等待,让某一个线程等待直到倒计时结束，在开始执行。比如：火箭发射，其他工序完成后，才开始倒计时发射操作。
 */
public class Main {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CheckThread checkThread = new CheckThread(countDownLatch);
        UltimateThread ultimateThread = new UltimateThread(countDownLatch);
        for(int i = 0; i < 10; i++) {
            executorService.submit(checkThread);
        }
        executorService.submit(ultimateThread);
        executorService.shutdown();
    }
}
