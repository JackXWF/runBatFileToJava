package com.example.demo.test.threadDemo;

/**
 * 交替打印ABC
 */
public class ThreadTest implements Runnable {
    static int count = 0;
    int threadId;//线程id
    String printContent;//输出内容
    private static final Object lock = new Object();

    public ThreadTest(int threadId, String printContent) {
        this.threadId = threadId;
        this.printContent = printContent;
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
                count = (count + 1) % 3;
                //唤醒其他线程
                lock.notifyAll();
            }

        }
    }
}
