package com.example.demo.test.threadPoolTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 优雅停机
 */

public class LoopTask {
    //任务集合
    private List<ChildTask> childTasks;

    public void init() {
        childTasks = new ArrayList<ChildTask>();
        childTasks.add(new ChildTask("task1"));
        childTasks.add(new ChildTask("task2"));


        for (ChildTask childTask : childTasks) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    childTask.doExecute();
                }
            }).start();
        }
    }


    /**
     * 关闭任务
     */
    public void shutDown() {
        if (childTasks.isEmpty()) return;
        for (ChildTask childTask : childTasks) {
            childTask.terminal();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        LoopTask loopTask = new LoopTask();

        //初始化
        loopTask.init();

        Thread.sleep(1000);

        //关闭任务
        loopTask.shutDown();
    }

}
