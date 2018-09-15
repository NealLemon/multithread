package base.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Neal
 * 士兵类
 */
public class Soldier implements Runnable{

    private int num;

    private final CyclicBarrier cyclicBarrier;

    public Soldier(int num, CyclicBarrier cyclicBarrier) {
        this.num = num;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("士兵" + num + ": 报道!");
            cyclicBarrier.await(); //第一次循环等待所有线程都执行完 执行 BarrierEndRun 类中的方法
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("士兵" + num + "： 任务完成!");
            cyclicBarrier.await(); //第二次循环等待所有线程都执行完 执行 BarrierEndRun 类中的方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
