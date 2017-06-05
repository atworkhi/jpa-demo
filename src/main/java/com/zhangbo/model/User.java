package com.zhangbo.model;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangbo on 2017/6/5.
 */

@Entity
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String realName;
    private Date createDate;
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public User setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public User setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public User setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}
