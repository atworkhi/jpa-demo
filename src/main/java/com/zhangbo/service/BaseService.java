package com.zhangbo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbo on 2017/5/9.
 */
public interface BaseService<T, ID extends Serializable> {


    void save(T entity);

    void delete(T entity);

    void delete(ID id);

    List<T> findAll();

    T findOne(ID id);

    Page<T> findAll(Pageable pageable);

    void save(List<T> list);


}
