package com.example.demo.test.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws Exception {
        // 创建一个包含10个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交10个任务给线程池执行，并记录每个任务的执行结果
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Integer> task = new MyTask1(i);
            Future<Integer> result = executor.submit(task);
            results.add(result);
        }

        // 等待所有任务执行完成
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        // 输出所有任务的执行结果
        int total = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Integer result = results.get(i).get();
                System.out.println("Task " + i + " result is " + result);
                total += result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("Task " + i + " execution error: " + e.getCause().getMessage());
            }
        }
        System.out.println("Total result is " + total);
    }
}

class MyTask1 implements Callable<Integer> {
    private int id;

    public MyTask1(int id) {
        this.id = id;
    }

    public Integer call() throws Exception {
        System.out.println("Task " + id + " is running");
        Thread.sleep(2000);  // 模拟任务执行时间
        if (id % 2 == 0) {
            throw new RuntimeException("Task " + id + " execution error");
        }
        return id * 10;
    }
}