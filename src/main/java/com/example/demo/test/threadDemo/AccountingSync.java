package com.example.demo.test.threadDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class AccountingSync implements Runnable {

    //static int i = 0;

    static AtomicInteger atomicInteger = new AtomicInteger(0);


    @Override
    public void run() {
        /*synchronized (AccountingSync.class) {
            for (int j = 0; j < 1000; j++) {
                atomicInteger.incrementAndGet();
            }
        }*/
        for (int j = 0; j < 1000; j++) {
            atomicInteger.incrementAndGet();
        }

    }


    public static void main(String[] args) {
        AccountingSync accountingSync = new AccountingSync();
        Thread t1 = new Thread(accountingSync);
        Thread t2 = new Thread(accountingSync);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(atomicInteger);
    }
}
