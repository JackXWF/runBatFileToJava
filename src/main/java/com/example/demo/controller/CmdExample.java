package com.example.demo.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class CmdExample {
    public static void main(String[] args) {
        String batFilePath = "E:\\scrcpy-win64-v2.0 (2)\\scrcpy-win64-v2.0\\scrcpy-console.bat";
        String ipAddress = "172.27.79.39";

        try {
            // 创建ProcessBuilder对象
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", batFilePath);

            // 设置工作目录（BAT文件所在的目录）
            processBuilder.directory(new File(batFilePath).getParentFile());

            // 启动子进程
            Process process = processBuilder.start();

            // 等待一段时间，确保CMD窗口已经打开
            Thread.sleep(1000);

            // 模拟键盘输入IP地址
            Runtime.getRuntime().exec("cmd /c echo " + ipAddress + "| clip"); //将ipAddress复制到Windows系统的剪贴板中
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);//模拟Ctrl+V
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);//模拟Enter
            robot.keyRelease(KeyEvent.VK_ENTER);

            // 等待用户手动关闭CMD窗口
            process.waitFor();

        } catch (IOException | InterruptedException | AWTException e) {
            e.printStackTrace();
        }
    }
}
