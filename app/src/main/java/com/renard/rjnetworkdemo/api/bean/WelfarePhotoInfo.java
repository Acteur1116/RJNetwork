package com.renard.rjnetworkdemo.api.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Riven_rabbit on 2018/12/3
 */
public class WelfarePhotoInfo {
    /**
     * _id : 57facc74421aa95de3b8ab6b
     * createdAt : 2016-10-10T07:02:12.35Z
     * desc : 10-10
     * publishedAt : 2016-10-10T11:41:33.500Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f8mssipb9sj20u011hgqk.jpg
     * used : true
     * who : daimajia
     */
    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String type;
    private String url;
    private String who;
    // 保存图片宽高
    private String pixel;

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "WelfarePhotoBean{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", who='" + who + '\'' +
                ", pixel='" + pixel + '\'' +
                '}';
    }
}
