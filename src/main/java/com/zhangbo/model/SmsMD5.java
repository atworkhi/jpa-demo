package com.zhangbo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_md5")
public class SmsMD5 {

    @Id
    private Integer id;
    private String inTime;
    private String md5;
    private String malewareName;
    private String category;
    private String platform;
    private String details;
    private String name;
    private String phoneNum;
    private String phoneLocal;
    private String phoneForm;
    private String email;
    private String emailPass;

    @Override
    public String toString() {
        return "SmsMD5{" +
                "id=" + id +
                ", inTime='" + inTime + '\'' +
                ", md5='" + md5 + '\'' +
                ", malewareName='" + malewareName + '\'' +
                ", category='" + category + '\'' +
                ", platform='" + platform + '\'' +
                ", details='" + details + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", phoneLocal='" + phoneLocal + '\'' +
                ", phoneForm='" + phoneForm + '\'' +
                ", email='" + email + '\'' +
                ", emailPass='" + emailPass + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SmsMD5 setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getInTime() {
        return inTime;
    }

    public SmsMD5 setInTime(String inTime) {
        this.inTime = inTime;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public SmsMD5 setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public String getMalewareName() {
        return malewareName;
    }

    public SmsMD5 setMalewareName(String malewareName) {
        this.malewareName = malewareName;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SmsMD5 setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public SmsMD5 setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public SmsMD5 setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getName() {
        return name;
    }

    public SmsMD5 setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public SmsMD5 setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getPhoneLocal() {
        return phoneLocal;
    }

    public SmsMD5 setPhoneLocal(String phoneLocal) {
        this.phoneLocal = phoneLocal;
        return this;
    }

    public String getPhoneForm() {
        return phoneForm;
    }

    public SmsMD5 setPhoneForm(String phoneForm) {
        this.phoneForm = phoneForm;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SmsMD5 setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmailPass() {
        return emailPass;
    }

    public SmsMD5 setEmailPass(String emailPass) {
        this.emailPass = emailPass;
        return this;
    }
}
