package base.readwritelock;


import java.io.*;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Stream;

/**
 * @author Neal
 */
public class ReadAndWriteDemo {

    private static File file;
    static
    {
        try {
            file = new File(ReadAndWriteDemo.class.getResource("readtest.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读方法
     * @param lock
     */
    public void handleRead(Lock lock) {
        BufferedReader bufferedReader = null;
        try {
            lock.lock();
            bufferedReader = new BufferedReader(new FileReader(file));
            Stream<String> stringStream= bufferedReader.lines();
            System.out.println("begin read");
            stringStream.forEach(System.out::println);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("end read");
            stringStream.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }

    }

    public void handleWrite(Lock lock,int i) {
       BufferedWriter writer = null;
        try{
            lock.lock();
            writer = new BufferedWriter(new FileWriter(file,true));
            System.out.println("begin write");
            TimeUnit.MILLISECONDS.sleep(1000);
            writer.newLine();
            writer.write(i);
            System.out.println("end write");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }



}
