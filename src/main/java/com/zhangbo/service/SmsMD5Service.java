package com.zhangbo.service;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;

import java.util.List;


public interface SmsMD5Service extends BaseService<SmsMD5, Integer> {


    List<SmsMD5Top> findTopSmsMD5(String field);


}
