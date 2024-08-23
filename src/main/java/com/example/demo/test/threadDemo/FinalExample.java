package com.example.demo.test.threadDemo;

public class FinalExample {
    int i;
    final int j;
    static FinalExample obj;

    public FinalExample() {
        i = 1;
        j = 2;
    }

    public static void write() {
        obj = new FinalExample();
    }

    public static void read() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
        System.out.println(a);
        System.out.println(b);
    }


    public static void main(String[] args) {


        //写
        new Thread(new Runnable() {
            public void run() {
                write();
            }
        }).start();

        //读
        new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        }).start();
    }
}
