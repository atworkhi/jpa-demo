package com.zhangbo.dao;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsMD5Dao extends BaseRepository<SmsMD5, Integer> {

    @Query(value = "select new com.zhangbo.model.SmsMD5Top(:field,count(field)) from SmsMD5  group by :field ")
    List<SmsMD5Top> findMD5Top(@Param("field") String field);

}
