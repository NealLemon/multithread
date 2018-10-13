# 多线程基础复习

#### 1.复习题一: 建立三个线程A、B、C，实现交替打印，即按照ABCABCABC的顺序打印。

  我们可以使用java多线程中的join,ReentrantLock,ReentrantLock -condition条件，synchronized分别实现。

- [Join实现](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/printABC/join)
- [ReentrantLock实现](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/printABC/relock)
- [ReentrantLock -condition条件实现](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/printABC/relock_condition)
- [synchronized实现](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/printABC/sync)



PS：使用join实现的时候，千万不要在各个线程中使用join，因为线程不是按顺序启动的，所以无法保障会按顺序输出。



#### 2.题目：创建20个线程,按每5个线程为一组输出内容。

信号量(Semaphore) 的使用, 信号量可以指定多个线程同时访问。

[信号量实现](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/printgroup)

####  3.CountDownLatch 多线程控制工具类

使用场景：火箭发射，其他工序完成后，才开始倒计时发射操作。

CheckThread为各项检查工序类，UltimateThread为最终发射类。

[CountDownLatch ](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/countdownlatch)

#### 4.可循环的执行器 CyclicBarrier

这里模拟的是士兵集合以及完成任务两个操作。

需要注意的是Demo中的CyclicBarrier(int parties, Runnable barrierAction) 这个构造函数， 第一个参数为 参与的线程总数，第二个参数为 一个动作类 指的是一次计数完成后 执行的动作。

[CyclicBarrierDemo](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/CyclicBarrier)

#### 5.读写锁ReentrantReadWriteLock 

读写锁的DMOE练习，是 《JAVA高并发程序设计》书中的第三章 3.1.4小节的内容，因为书上的例子做了模拟，我自己写了读写文件的方法，通过查看文件的读写内容可以快速的看出是否读写锁具有高效的性质。

[ReentrantReadWriteLock](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/readwritelock)

#### 6.fork/join框架

参考 http://ifeve.com/fork-join-3/

开发一个在文档中查找单词的应用程序。你将会实现以下两种任务类型：
- 1.一个文档任务，将在文档中的行集合中查找一个单词。
- 2.一个行任务，将在文档的一部分数据中查找一个单词。
- 3.所有任务将返回单词在文档的一部分中或行中出现的次数。

这个demo写完之后，效率总是不高。

[fork/join框架](https://github.com/NealLemon/multithread/tree/master/src/main/java/base/forkjoin)

