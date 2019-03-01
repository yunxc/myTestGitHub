package com.yun.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author yunlong.zhang
 * @date 2019/2/28 13:32
 */
//实现redis得继承Serializable接口进行序列化
public class User implements Serializable {
    //定义一个序列号
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    private String headPhoto; //头像图片

    private String src; //头像图片

    private String kind;

    private String createTime;

    private Integer status;//1正常2删除

    private List<Map<String,Object>> li;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Map<String, Object>> getLi() {
        return li;
    }

    public void setLi(List<Map<String, Object>> li) {
        this.li = li;
    }
}
