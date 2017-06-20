package com.zhangbo.service;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;

import java.util.List;


public interface SmsMD5Service extends BaseService<SmsMD5, Integer> {


    List<SmsMD5Top> findTopByMD5();

    List<SmsMD5Top> findTopByPhoneNum();

    List<SmsMD5Top> findTopByIp();

}
