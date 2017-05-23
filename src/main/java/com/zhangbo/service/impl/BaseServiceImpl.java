package com.zhangbo.service.impl;

import com.zhangbo.dao.BaseRepository;
import com.zhangbo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbo on 2017/5/9.
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;


    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    public void save(T entity) {
        baseRepository.save(entity);
    }

    public void save(List<T> list) {
        baseRepository.save(list);
    }

    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    public void delete(ID id) {
        baseRepository.delete(id);
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

}
