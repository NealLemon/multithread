package base.forkjoin;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    //模拟的文章二维字符串数组
    private String document[][];

    //
    private int start, end;

    //要查找的单词
    private String word;


    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if(end -start < 0) {
            result = processLines(document, start, end, word);
        }else{
            int mid = (start+end)/2;
            DocumentTask task1 = new DocumentTask(document,start,mid,word);
            DocumentTask task2 = new DocumentTask(document,mid,end,word);
           //Forks the given tasks, returning when isDone holds for each task or an (unchecked) exception is encountered,
            // in which case the exception is rethrown.
            invokeAll(task1,task2);
            try {
                result=groupResults(task1.get(),task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    private int groupResults(Integer integer, Integer integer1) {
        Integer result = 0;
        result=integer+integer1;
        return result;
    }


    private int processLines(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<LineTask>();
        for (int i=start; i < end; i++){
            LineTask task=new LineTask(document[i], 0, document[i].length, word);
            tasks.add(task);
        }
        invokeAll(tasks);

        int result = 0;
        for (LineTask task : tasks) {
            try{
                result += task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return new Integer(result);
    }
}
