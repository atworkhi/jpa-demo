package com.zhangbo.dao;

import com.zhangbo.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangbo on 2017/6/5.
 */
@Repository
public interface UserDao extends BaseRepository<User, Integer> {

    User findUserByUserName(String username);


}
