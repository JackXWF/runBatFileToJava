package com.example.demo.test.threadDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < threadPoolExecutor.getCorePoolSize(); i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    for (int j = 0; j < 2; j++) {
                        System.out.println("线程池执行" + Thread.currentThread().getName() + ":" + j);

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }
            });
        }


        threadPoolExecutor.shutdown();
    }
}
