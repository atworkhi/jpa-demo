package com.zhangbo.dao;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Repository
public interface SmsMD5Dao extends BaseRepository<SmsMD5, Integer> {

    @Query(value = "select s.id from SmsMD5 s",nativeQuery = true)
    List<SmsMD5Top> findTopSmsMD5(@Param("field") String field);

}
