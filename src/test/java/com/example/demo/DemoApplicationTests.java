package com.example.demo;

import com.example.demo.dao.TaUserMapper;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    TaUserMapper taUserMapper;

    @Test
    void contextLoads() {
        User taUser = new User();
        taUser.setGender(true);
        taUser.setUsername("Jack");
        taUserMapper.insert(taUser);
    }

}
