package com.zhangbo.dao;

import com.zhangbo.model.SmsDown;
import com.zhangbo.model.SmsMD5Top;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Repository
public interface SmsDownDao extends BaseRepository<SmsDown, Integer> {


    /**
     * 域名 排行
     * @return
     */
    @Query(value = "select new com.zhangbo.model.SmsMD5Top(s.downloadForm,count(s.downloadForm)) from SmsDown as s where s.downloadForm is not null and s.downloadForm <> '' group by s.downloadForm order by count(s.downloadForm) desc ")
    List<SmsMD5Top> findDomainTop();

    /**
     * ip 排行
     * @return
     */
    @Query(value = "select new com.zhangbo.model.SmsMD5Top(s.downloadIp,count(s.downloadIp)) from SmsDown as s where s.downloadIp is not null and s.downloadIp <> '' group by s.downloadIp order by count(s.downloadIp) desc ")
    List<SmsMD5Top> findIpTop();

}
