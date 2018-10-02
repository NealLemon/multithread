package base.printgroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Neal
 */
public class Main {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);   //允许最多5个线程同时访问
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemapDemo semapDemo = new SemapDemo(semaphore);
        for(int i = 0 ; i < 20; i++) {
            executorService.execute(semapDemo);
        }
        executorService.shutdown();

    }
}
