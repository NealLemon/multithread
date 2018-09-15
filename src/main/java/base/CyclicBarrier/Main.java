package base.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Neal
 * 主要练习 CyclicBarrier 类 顾名思义 可以循环的执行计时器
 * 这里需要注意的是 CyclicBarrier(int parties, Runnable barrierAction) 这个构造函数。
 */
public class Main {

    public static void main(String[] args) {
        final int num = 10;  //初始化士兵数量
        /**
         *  构造方法 第一个参数为 参与的线程总数
         *  第二个参数为 一个动作类 指的是一次计数完成后 执行的动作
         */

        CyclicBarrier cyclicBarrier = new CyclicBarrier(num,new BarrierEndRun(num));

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Soldier(i,cyclicBarrier));
        }
        executorService.shutdown();


    }
}
