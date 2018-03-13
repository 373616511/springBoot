package com.asyf.springboot.service.serviceImpl;

import com.asyf.springboot.dao.UserDao;
import com.asyf.springboot.entity.User;
import com.asyf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectByPrimaryKey(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserById(User user) {
        userDao.updateUserById(user);
        String a = null;
        //System.err.println(a.toString());
    }
}
