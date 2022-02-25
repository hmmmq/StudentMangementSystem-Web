package com.niit.service.impl;

import com.niit.mapper.UserMapper;
import com.niit.pojo.User;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public int checkUser(User user) {
        if(user==null)
            return 0;
        if(user.getLoginid()==null)
            return 0;
        if(user.getPassword()==null)
            return 0;
        if(userMapper.selectUser(user)!=null){
            if(user.getLoginid()==0)
                return 1;
            else
                return 2;
            }
         else return 0;
    }
}
