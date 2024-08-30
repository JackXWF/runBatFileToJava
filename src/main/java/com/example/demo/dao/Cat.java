package com.example.demo.dao;

import lombok.Data;

@Data
public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }
}
