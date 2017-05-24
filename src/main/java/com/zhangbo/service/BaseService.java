package com.zhangbo.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbo on 2017/5/9.
 */
public interface BaseService<T, ID extends Serializable> {


    void save(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void delete(ID id) throws Exception;

    void delete(Iterable<T> entities);

    List<T> findAll() throws Exception;

    T findOne(ID id) throws Exception;

    Page<T> findAll(Pageable pageable) throws Exception;

    void save(List<T> list) throws Exception;

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) throws Exception;

    List<T> findAll(Iterable<ID> ids) throws Exception;

}
