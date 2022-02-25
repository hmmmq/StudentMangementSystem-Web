package com.niit.mapper;

import com.niit.pojo.User;

public interface UserMapper {
    User selectUser(User user);
    int updateUser(User user);
}