package com.example.demo.test.threadPoolTest;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);


        for (int i = 0; i < 5; i++) {
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程池执行" + Thread.currentThread().getName() + "现在时间:" + new Date());
                }
            }, 1, TimeUnit.MINUTES);
        }


        scheduledExecutorService.shutdown();
    }
}
