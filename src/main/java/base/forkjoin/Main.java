package base.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author Neal
 * 关于fork/join框架的使用  参考 http://ifeve.com/fork-join-3/
 * 开发一个在文档中查找单词的应用程序。你将会实现以下两种任务类型：
 * 1.一个文档任务，将在文档中的行集合中查找一个单词。
 * 2.一个行任务，将在文档的一部分数据中查找一个单词。
 * 所有任务将返回单词在文档的一部分中或行中出现的次数。
 */
public class Main {

    public static void main(String[] args) {
        Document mock=new Document();
        String[][] document=mock.generateDocument(100, 1000, "the");
        DocumentTask task=new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();

        try {
            System.out.printf("Main: The word appears %d in the document",task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d in the document",task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
