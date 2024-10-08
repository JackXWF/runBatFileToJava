package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.enums.GenderEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName ta_user
 */
//开启全局表名前缀
@TableName(keepGlobalPrefix = true)
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private GenderEnum gender;

    @TableField("isMarried")
    private Boolean isMarried;


    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}