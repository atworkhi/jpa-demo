package com.zhangbo.dao;

import com.zhangbo.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangbo on 2017/6/5.
 */
public interface UserDao {

    User findUserByUserName(String username);


}
