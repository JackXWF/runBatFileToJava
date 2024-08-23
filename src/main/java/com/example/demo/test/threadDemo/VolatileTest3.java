package com.example.demo.test.threadDemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest3 {
    public AtomicInteger inc = new AtomicInteger(0);


    public void increase() {
        inc.incrementAndGet();//+1
    }

    public static void main(String[] args) {
        final VolatileTest3 test = new VolatileTest3();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            };
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join(); //等待子线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(test.inc);
    }
}
