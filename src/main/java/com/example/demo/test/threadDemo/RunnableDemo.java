package com.example.demo.test.threadDemo;

public class RunnableDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask(i);
            Thread thread = new Thread(myTask);
            thread.start();
        }
    }
}

class MyTask implements Runnable {
    private int i;

    public MyTask(int i) {
        this.i = i;
    }


    @Override
    public void run() {
        System.out.println("线程" + i + "正在运行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
