package com.example.demo.test.threadDemo;

/**
 * 交替打印ABC
 */
public class ThreadTest2 implements Runnable {
    static int count = 0;
    int threadId;//线程id
    String printContent;//输出内容
    static int totalThread;
    private static final Object lock = new Object();

    public ThreadTest2(int threadId, String printContent, int totalThread) {
        this.threadId = threadId;
        this.printContent = printContent;
        ThreadTest2.totalThread = totalThread;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (count != threadId) {
                    //如果当前线程id与count不一致，则等待
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(printContent);

                //count自增，取模3，保证线程id在0-2之间
                count = (count + 1) % ThreadTest2.totalThread;
                //唤醒其他线程
                lock.notifyAll();
            }

        }
    }


    public static void main(String[] args) {
        String[] contents = {"A", "B", "C", "D", "E"};

        Thread[] threads = new Thread[contents.length];

        for (int i = 0; i < contents.length; i++) {
            threads[i] = new Thread(new ThreadTest2(i, contents[i], contents.length));
            threads[i].start();
        }
    }
}
