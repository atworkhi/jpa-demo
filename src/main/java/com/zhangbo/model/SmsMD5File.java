package com.zhangbo.model;


public class SmsMD5File {

    private Integer id;
    private String fileName;

    public SmsMD5File() {
    }

    public SmsMD5File(Integer id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public SmsMD5File(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public SmsMD5File setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public SmsMD5File setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
