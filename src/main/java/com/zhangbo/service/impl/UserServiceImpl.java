package com.zhangbo.service.impl;

import com.zhangbo.dao.UserDao;
import com.zhangbo.model.User;
import com.zhangbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangbo on 2017/6/5.
 */
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    private UserDao userDao;

    @Override
    public User findUserByUserName(String username) throws Exception {
        return userDao.findUserByUserName(username);
    }
}
