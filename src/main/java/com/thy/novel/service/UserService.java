package com.thy.novel.service;

import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserItem;

/**
 * Author: thy
 * Date: 2022/3/17 10:23
 */
public interface UserService {
    /**
     * 验证用户信息
     * */
    ResponseItem<UserItem> checkUser(String username, String password, boolean withToken);

    /**
     * 新增用户
     * */
    ResponseItem<UserItem> addUser(String name, String password, String email);

    /**
     * 发送邮件重置密码
     * */
    ResponseItem<UserItem> sendMail(String email);
}
