package com.example.demo.test.threadPoolTest;

import com.example.demo.dao.Cat;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class ChildTask {
    private final int POOL_SIZE = 3;
    private final int SPLIT_SIZE = 4;

    // 接收jvm关闭信号，实现优雅停机
    protected volatile boolean terminal = false;


    private final String taskName;

    public ChildTask(String taskName) {
        this.taskName = taskName;
    }


    public void doExecute() {
        int count = 0;
        while (true) {
            System.out.println(taskName + "开始" + count);
            //获取数据
            List<Cat> list = queryData();
            taskExecute(list);
            System.out.println(taskName + "结束" + count);

            //收到关机信号，退出循环
            if (terminal) {
                break;
            }

            count++;

        }
        //回收线程资源
        System.out.println(taskName + " 线程池回收");
        TaskProcessUtil.removeThreadPool(taskName);
    }

    // 优雅停机
    public void terminal() {
        // 关机
        terminal = true;
        System.out.println(taskName + " shut down");
    }

    private void taskExecute(List<Cat> list) {
        if (list.isEmpty()) {
            return;
        }


        //拆分需要处理的数据
        List<List<Cat>> splitDataList = Lists.partition(list, SPLIT_SIZE);
        final CountDownLatch countDownLatch = new CountDownLatch(splitDataList.size());

        //并发处理理拆分的数据，共用一个线程池
        for (final List<Cat> splitData : splitDataList) {
            ExecutorService threadPool = TaskProcessUtil.getThreadPool(taskName, POOL_SIZE);
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    //处理单个任务
                    doProcess(splitData, countDownLatch);
                }
            });
        }

        try {
            //等待所有任务处理完成；就是countDownLatch的计数器变成0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doProcess(List<Cat> splitData, CountDownLatch countDownLatch) {
        try {
            for (Cat splitDatum : splitData) {
                //对分片数据进行处理
                System.out.println("任务名：" + taskName + "处理数据：" + splitDatum.getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //计数器减一
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    private List<Cat> queryData() {
        List<Cat> list = new ArrayList<Cat>();
        list.add(new Cat("cat1"));
        list.add(new Cat("cat2"));
        list.add(new Cat("cat3"));
        list.add(new Cat("cat4"));
        list.add(new Cat("cat5"));
        list.add(new Cat("cat6"));
        list.add(new Cat("cat7"));
        list.add(new Cat("cat8"));
        list.add(new Cat("cat9"));
        list.add(new Cat("cat10"));

        return list;
    }

}
