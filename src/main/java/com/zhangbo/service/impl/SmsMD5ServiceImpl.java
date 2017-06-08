package com.zhangbo.service.impl;

import com.zhangbo.dao.SmsMD5Dao;
import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;
import com.zhangbo.service.SmsMD5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Service
public class SmsMD5ServiceImpl extends BaseServiceImpl<SmsMD5, Integer> implements SmsMD5Service {

    @Autowired
    private SmsMD5Dao smsMD5Dao;

    @Override
    public List<SmsMD5Top> findTopSmsMD5(String field) {
        return smsMD5Dao.findTopSmsMD5(field);
    }
}
