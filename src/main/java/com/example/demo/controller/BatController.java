package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class BatController {

    @PostMapping("/api/execute-bat")
    public String executeBat() {
        String batPath = "E:\\scrcpy-console.bat";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c start " + batPath);

            // 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
            return "退出码: " + exitCode;
        } catch (Exception e) {
            e.printStackTrace();
            return "执行出错: " + e.getMessage();
        }
    }
}