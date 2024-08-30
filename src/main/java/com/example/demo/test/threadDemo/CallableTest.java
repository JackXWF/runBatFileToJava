package com.example.demo.test.threadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    private void test() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "123";
            }
        };

        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();

        try {
            if(futureTask.isDone()) {
                //任务完成，返回结果
                System.out.println(futureTask.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        CallableTest callableTest = new CallableTest();
        callableTest.test();
    }
}
