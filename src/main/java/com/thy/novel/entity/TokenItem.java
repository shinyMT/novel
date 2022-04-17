package com.thy.novel.entity;

import java.io.Serializable;

/**
 * Author: thy
 * Date: 2022/3/23 16:16
 * 登陆token
 */
public class TokenItem implements Serializable {
    private static final long serialVersionUID = -8346237490588060040L;

    // tokenID
    private int id;
    // 用户ID
    private int userId;
    // 过期时间
    private String expireTime;
    // token
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
