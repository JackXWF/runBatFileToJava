package com.example.demo.controller;


import java.io.*;

public class TestBat {
    public static void main(String[] args) {
        String batFilePath = "C:\\Users\\Jack\\Downloads\\scrcpy-win64-v2.6.1\\scrcpy-win64-v2.6.1\\scrcpy-console.bat";
        String ipAddress = "172.27.98.91";

        try {
            // 创建一个临时批处理文件来执行原始批处理文件
            File tempFile = File.createTempFile("temp", ".bat");
            tempFile.deleteOnExit();

            try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
                writer.println("@echo off");
                writer.println("cd /d " + new File(batFilePath).getParent());
                writer.println("call " + batFilePath + " " + ipAddress);
                writer.println("pause");
            }

            // 使用ProcessBuilder执行临时批处理文件
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", tempFile.getAbsolutePath());
            processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}