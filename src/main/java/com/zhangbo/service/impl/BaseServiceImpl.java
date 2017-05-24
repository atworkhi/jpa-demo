package com.zhangbo.service.impl;

import com.zhangbo.dao.BaseRepository;
import com.zhangbo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbo on 2017/5/9.
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;


    public T findOne(ID id) throws Exception {
        return baseRepository.findOne(id);
    }

    @Transactional
    public void save(T entity) throws Exception {
        baseRepository.save(entity);
    }

    @Transactional
    public void save(List<T> list) throws Exception {
        baseRepository.save(list);
    }

    @Transactional
    public void delete(T entity) throws Exception {
        baseRepository.delete(entity);
    }

    @Transactional
    public void delete(ID id) throws Exception {
        baseRepository.delete(id);
    }

    @Override
    public void delete(Iterable<T> entities) {
        baseRepository.delete(entities);
    }

    public List<T> findAll() throws Exception {
        return baseRepository.findAll();
    }

    public Page<T> findAll(Pageable pageable) throws Exception {
        return baseRepository.findAll(pageable);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) throws Exception {
        return baseRepository.findAll(example, pageable);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) throws Exception {
        return baseRepository.findAll(ids);
    }
}
