package com.zhangbo.service;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SmsMD5Service extends BaseService<SmsMD5, Integer> {


    @Query(value = "select new com.zhangbo.model(md5,count(md5)) from SmsMD5  group by :field")
    List<SmsMD5Top> findTopSmsMD5(@Param("field") String field);


}
