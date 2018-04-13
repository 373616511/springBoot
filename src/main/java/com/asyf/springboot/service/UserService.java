package com.asyf.springboot.service;

import com.asyf.springboot.entity.User;

public interface UserService {
    User selectByPrimaryKey(String s);

    void updateUserById(User user);

    void update(User user);
}
