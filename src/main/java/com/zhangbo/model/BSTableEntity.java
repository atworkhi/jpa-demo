package com.zhangbo.model;

import java.util.List;

/**
 * Created by zhangbo on 2017/5/9.
 */
public class BSTableEntity<T> {

    private Long total;

    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
