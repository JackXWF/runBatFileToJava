package com.example.demo.test.threadPoolTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 */
public class TaskProcessUtil {

    //线程池集合，每个任务有自己的线程池
    private static Map<String, ExecutorService> threadPoolMap = new HashMap<String, ExecutorService>();


    private static ExecutorService initThreadpool(String poolName, int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public static ExecutorService getThreadPool(String poolName, int poolSize) {
        ExecutorService executorService = threadPoolMap.get(poolName);
        if (executorService == null) {
            synchronized (TaskProcessUtil.class) {
                executorService = threadPoolMap.get(poolName);
                if (executorService == null) {
                    //两次判空，防止并发时重复创建线程池  （双重检查锁定）
                    executorService = initThreadpool(poolName, poolSize);
                    threadPoolMap.put(poolName, executorService);
                }
            }

        }

        return executorService;
    }


    public static void removeThreadPool(String poolName) {
        ExecutorService remove = threadPoolMap.remove(poolName);
        if (remove != null) {
            remove.shutdown();
        }
    }


}
