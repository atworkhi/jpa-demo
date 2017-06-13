package com.zhangbo.model;


public class SmsMD5Top {

    private Integer id;
    private String md5;
    private long count;

    public Integer getId() {
        return id;
    }

    public SmsMD5Top() {
    }

    public SmsMD5Top(String md5, long count) {
        this.md5 = md5;
        this.count = count;
    }

    public SmsMD5Top setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public SmsMD5Top setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public long getCount() {
        return count;
    }

    public SmsMD5Top setCount(long count) {
        this.count = count;
        return this;
    }
}
