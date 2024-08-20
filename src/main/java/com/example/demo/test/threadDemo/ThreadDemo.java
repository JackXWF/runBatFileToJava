package com.example.demo.test.threadDemo;

public class ThreadDemo {
    public static void main(String[] args) {
        // 创建10个线程并启动
        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread(i);
            thread.start();
        }
    }
}

class MyThread extends Thread {
    private int id;

    public MyThread(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Thread " + id + " is running");
        try {
            Thread.sleep(1000);  // 模拟任务执行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}