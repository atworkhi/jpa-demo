package com.zhangbo.service.impl;

import com.zhangbo.dao.SmsDownDao;
import com.zhangbo.model.SmsDown;
import com.zhangbo.service.SmsDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Service
public class SmsDownServiceImpl extends BaseServiceImpl<SmsDown, Integer> implements SmsDownService {

    @Autowired
    private SmsDownDao smsDownDao;




}
