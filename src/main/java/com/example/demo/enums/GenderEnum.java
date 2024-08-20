package com.example.demo.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GenderEnum {
    MAN(1,"男"),
    WOMAN(2,"女")
    ;



    @EnumValue
    private final Integer value;
    @JsonValue
    private final String desc;

    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


}
