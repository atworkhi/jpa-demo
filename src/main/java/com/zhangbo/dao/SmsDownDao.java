package com.zhangbo.dao;

import com.zhangbo.model.SmsDown;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Repository
public interface SmsDownDao extends BaseRepository<SmsDown, Integer> {

}
