package base.forkjoin;

import java.util.Random;

/**
 * @author Neal
 * 产生用来模拟文档的字符串的二维数组
 */
public class Document {

    //一个带有一些单词的字符串数组。这个数组将被用来生成字符串二维数组
    private String words[]={"the","hello","goodbye","package", "java","thread","pool","random","class","main"};

    /**
     *
     * @param numLines  行数
     * @param numWords 每行的单词书
     * @param word  要查找的单词
     * @return  一个字符串二维数组，来表示将要查找的单词
     */
    public String[][] generateDocument(int numLines, int numWords,String word) {
        int counter=0;
        String document[][]=new String[numLines][numWords];
        Random random=new Random();
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)) {
                    counter++;
                }
            }
        }
        System.out.println("DocumentMock: The word appears "+counter+" times in the document");
        return document;
    }
}
