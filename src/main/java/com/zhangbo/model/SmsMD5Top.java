package com.zhangbo.model;


public class SmsMD5Top {

    private String md5;
    private long total;

    public SmsMD5Top() {
    }

    public SmsMD5Top(String md5, long total) {
        this.md5 = md5;
        this.total = total;
    }


    public String getMd5() {
        return md5;
    }

    public SmsMD5Top setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public SmsMD5Top setTotal(long total) {
        this.total = total;
        return this;
    }
}
