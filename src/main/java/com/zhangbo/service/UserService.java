package com.zhangbo.service;

import com.zhangbo.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by zhangbo on 2017/6/5.
 */
public interface UserService {

    User findUserByUserName(String username) throws Exception;
}
