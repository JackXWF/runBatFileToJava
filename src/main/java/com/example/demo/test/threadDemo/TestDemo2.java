package com.example.demo.test.threadDemo;

public class TestDemo2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadTest(0, "A"));
        Thread thread1 = new Thread(new ThreadTest(1, "B"));
        Thread thread2 = new Thread(new ThreadTest(2, "C"));


        thread.start();
        thread1.start();
        thread2.start();
    }
}
