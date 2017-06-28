package com.zhangbo.service;

import com.zhangbo.model.SmsDown;
import com.zhangbo.model.SmsMD5Top;

import java.util.List;


/**
 * Created by zhangbo on 2017/5/8.
 */
public interface SmsDownService extends BaseService<SmsDown,Integer> {

    List<SmsMD5Top> findTopByIp();

    List<SmsMD5Top> findTopByDomain();

}
