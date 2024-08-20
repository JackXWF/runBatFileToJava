package com.example.demo;

import com.example.demo.dao.TaUserMapper;
import com.example.demo.entity.User;
import com.example.demo.enums.GenderEnum;
import com.example.demo.service.TaUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    TaUserMapper taUserMapper;

    @Autowired
    TaUserService taUserService;

    @Test
    void contextLoads() {
        User taUser = new User();
        taUser.setGender(GenderEnum.MAN);
        taUser.setUsername("Jack");
        taUser.setIsMarried(false);
        taUserMapper.insert(taUser);
    }

    @Test
    void deleteUser() {
        taUserService.removeById("1822824253766393857");
    }

    @Test
    void userList() {
        List<User> list = taUserService.list();
        list.forEach(System.out::println);
    }

}
