package base.printABC.join;

import java.util.concurrent.TimeUnit;

public class BaseThread extends Thread{

    private String letter;

    public BaseThread(String letter) {
        this.letter = letter;
    }

    public void run() {
        System.out.println(letter);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
