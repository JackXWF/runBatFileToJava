package com.example.demo.test;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestDemo {
    public static void main(String[] args) {
        //虽然String也是引用类型，但是这里是传递的值，不是引用
        String a = "aa";

        //引用类型传递地址
        String[] b = {"cc", "dd", "ee", "ff"};
        test(a, b);

        //System.out.println(a);
        for (String s : b) {
            System.out.println(s);
        }



    }

    public static void test(String a, String[] b) {
        a = "bbb";
        b[0] = "zz";
    }


}
