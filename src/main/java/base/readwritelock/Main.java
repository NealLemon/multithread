package base.readwritelock;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Neal
 * 本例为 读写锁的DMOE练习，是 《JAVA高并发程序设计》书中的第三章 3.1.4小节的内容。
 * 因为书上的例子做了模拟，我自己写了读写文件的方法，通过查看文件的读写内容可以快速的看出是否读写锁具有高效的性质。
 *
 * 结论：如果单纯的可重入锁，那么需要等待程序20多秒才可以全部读写完成。
 *       如果是读写锁,那么只需要简单的2~3秒钟，就可以全部读写完成。
 */
public class Main {

    //可重入锁
    private static Lock lock = new ReentrantLock();

    //声明读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读锁
    private static Lock readLock = readWriteLock.readLock();

    //写锁
    private static Lock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {

        final ReadAndWriteDemo readAndWriteDemo = new ReadAndWriteDemo();

        Runnable readRunnbale = new Runnable() {
            @Override
            public void run() {
                //可重入锁的条件下
               // readAndWriteDemo.handleRead(lock);
                //读锁
                readAndWriteDemo.handleRead(readLock);
            }
        };

        Runnable writeRunnbale = new Runnable() {
            @Override
            public void run() {
                //可重入锁的条件下 writeLock
               // readAndWriteDemo.handleWrite(lock,new Random().nextInt());
                //写锁
                readAndWriteDemo.handleWrite(writeLock,new Random().nextInt());
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 18; i++) {
            executorService.execute(readRunnbale);
        }

        for(int i = 18; i < 20; i++) {
            executorService.execute(writeRunnbale);
        }

        executorService.shutdown();

    }
}
