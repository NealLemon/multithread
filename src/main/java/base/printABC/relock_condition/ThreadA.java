package base.printABC.relock_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadA implements Runnable {
    //原子性 integer对象
    private AtomicInteger count;

    //重入锁
    private ReentrantLock lock;

    //下一个condition
    private Condition next;

    //当前线程condition
    private Condition self;

    //构造函数
    public ThreadA(AtomicInteger count, ReentrantLock lock, Condition next, Condition self) {
        this.count = count;
        this.lock = lock;
        this.next = next;
        this.self = self;
    }

    public void run() {
        try {
            lock.lock();    //加锁 在使用condition 条件之前 必须加锁
            while(true) {
                /**
                 * begin 如果不是1 则A condition 释放当前锁
                 */
                if (count.get() != 1)
                    self.await();
                /**
                 * end
                 */

                /**
                 * 如果当前数值为 1 则 进行输出
                 */
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("A");
                count.set(2);   // 将数值设为下一个条件所需的数值
                next.signal();   //唤醒下一个锁
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



    }

