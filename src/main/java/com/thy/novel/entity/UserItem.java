package com.thy.novel.entity;

import java.io.Serializable;

/**
 * Author: thy
 * Date: 2022/3/17 10:19
 * 用户信息条目
 */
public class UserItem implements Serializable {
    private static final long serialVersionUID = 4061799160326970990L;

    // 用户名
    private String name;
    // 密码
    private String password;
    // 邮箱
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
