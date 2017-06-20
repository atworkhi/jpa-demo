package com.zhangbo.dao;

import com.zhangbo.model.SmsMD5;
import com.zhangbo.model.SmsMD5Top;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsMD5Dao extends BaseRepository<SmsMD5, Integer> {

    /**
     * md5 排行榜
     *
     * @return
     */
    @Query(value = "select new com.zhangbo.model.SmsMD5Top(s.md5,count(s.md5)) from SmsMD5 as s group by s.md5 order by count(s.md5) desc")
    List<SmsMD5Top> findMD5Top();

    /**
     * 手机号排行
     *
     * @return
     */
    @Query(value = "select new com.zhangbo.model.SmsMD5Top(s.phoneNum,count(s.phoneNum)) from SmsMD5 as s group by s.phoneNum order by count(s.phoneNum) desc ")
    List<SmsMD5Top> findPhoneNumTop();

    /**
     * ip 排行
     * @return
     */
    @Query(value = "select new com.zhangbo.model.SmsMD5Top(s.downloadIp,count(s.downloadIp)) from SmsDown as s group by s.downloadIp order by count(s.downloadIp) desc ")
    List<SmsMD5Top> findIpTop();


}
