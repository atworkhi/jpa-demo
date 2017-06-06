package com.zhangbo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sms_down")
public class SmsDown implements Serializable {

    /**
     * 导出csv表头
     */
    @Transient
    public static final String[] EXPORT_HEADERS = {"研判时间", "病毒md5", "病毒名称", "病毒真实下载地址",
            "病毒真实下载IP", "病毒真实下载归属地", "病毒真实下载运营商", "病毒原始下载地址",
            "病毒属性", "影响平台", "备注"};

    /**
     * 导出csv表头对应的字段名
     */
    @Transient
    public static final String[] EXPORT_HEADERS_FIELDS = {"inTime", "md5", "malewareName", "downloadUrl",
            "downloadIp", "downloadLocal", "downloadForm", "oldDownload",
            "category", "platform", "description"};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String inTime;

    private String md5;

    private String malewareName;

    private String downloadUrl;

    private String downloadIp;

    private String downloadLocal;

    private String downloadForm;

    private String oldDownload;

    private String category;

    private String platform;

    private String description;


    @Override
    public String toString() {
        return "SmsDown{" +
                "id=" + id +
                ", inTime='" + inTime + '\'' +
                ", md5='" + md5 + '\'' +
                ", malewareName='" + malewareName + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", downloadIp='" + downloadIp + '\'' +
                ", downloadLocal='" + downloadLocal + '\'' +
                ", downloadForm='" + downloadForm + '\'' +
                ", oldDownload='" + oldDownload + '\'' +
                ", category='" + category + '\'' +
                ", platform='" + platform + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public SmsDown setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getInTime() {
        return inTime;
    }

    public SmsDown setInTime(String inTime) {
        this.inTime = inTime;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public SmsDown setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public String getMalewareName() {
        return malewareName;
    }

    public SmsDown setMalewareName(String malewareName) {
        this.malewareName = malewareName;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public SmsDown setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public String getDownloadIp() {
        return downloadIp;
    }

    public SmsDown setDownloadIp(String downloadIp) {
        this.downloadIp = downloadIp;
        return this;
    }

    public String getDownloadLocal() {
        return downloadLocal;
    }

    public SmsDown setDownloadLocal(String downloadLocal) {
        this.downloadLocal = downloadLocal;
        return this;
    }

    public String getDownloadForm() {
        return downloadForm;
    }

    public SmsDown setDownloadForm(String downloadForm) {
        this.downloadForm = downloadForm;
        return this;
    }

    public String getOldDownload() {
        return oldDownload;
    }

    public SmsDown setOldDownload(String oldDownload) {
        this.oldDownload = oldDownload;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SmsDown setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public SmsDown setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SmsDown setDescription(String description) {
        this.description = description;
        return this;
    }
}
