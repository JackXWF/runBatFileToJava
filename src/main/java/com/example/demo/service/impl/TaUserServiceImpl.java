package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.dao.TaUserMapper;
import com.example.demo.service.TaUserService;
import org.springframework.stereotype.Service;

/**
* @author Jack
* @description 针对表【ta_user】的数据库操作Service实现
* @createDate 2024-08-12 09:53:44
*/
@Service
public class TaUserServiceImpl extends ServiceImpl<TaUserMapper, User>
    implements TaUserService {

}




