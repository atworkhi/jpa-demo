package com.zhangbo.service.impl;

import com.zhangbo.dao.SmsDownDao;
import com.zhangbo.model.SmsDown;
import com.zhangbo.model.SmsMD5Top;
import com.zhangbo.service.SmsDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Service
public class SmsDownServiceImpl extends BaseServiceImpl<SmsDown, Integer> implements SmsDownService {

    @Autowired
    private SmsDownDao smsDownDao;

    @Override
    public List<SmsMD5Top> findTopByIp() {
        return smsDownDao.findIpTop();
    }

    @Override
    public List<SmsMD5Top> findTopByDomain() {
        return smsDownDao.findDomainTop();
    }
}
