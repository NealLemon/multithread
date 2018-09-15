package base.printABC.join;

/**
 * @author Neal
 * 解答要点: join 的应用。
 *          注意千万不要把join放在线程方法内执行，因为start方法是无序的
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        while(true) {
            BaseThread ta = new BaseThread("A");
            BaseThread tb = new BaseThread("B");
            BaseThread tc = new BaseThread("C");
            ta.start();
            ta.join();
            tb.start();
            tb.join();
            tc.start();
            tc.join();

        }
    }
}
