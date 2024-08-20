package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.TaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final TaUserService taUserService;


    @GetMapping("/getList")
    public List<User> getUseList() {
        return taUserService.list();
    }
}
