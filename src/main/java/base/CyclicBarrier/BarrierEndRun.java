package base.CyclicBarrier;

/**
 * @author Neal
 * 当所有持有 CyclicBarrier 类执行完后 执行该类
 */
public class BarrierEndRun implements Runnable{

    private int num;

    private boolean flag = false;

    public BarrierEndRun(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        if(flag) {
            System.out.println("士兵 【"+num +"】任务完成!");  //第二次所有线程await后
        }else{
            System.out.println("士兵 【"+num +"】集合完毕!");  //第一次所有线程await后
            flag = true;
        }
    }
}
