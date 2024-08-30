package com.example.demo.test.threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        //核心线程数量就是最大线程数量
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }



        executorService.shutdownNow();
    }
}
