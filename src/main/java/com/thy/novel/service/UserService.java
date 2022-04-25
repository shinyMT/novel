package com.thy.novel.service;

import com.thy.base.result.ResultBody;
import com.thy.novel.entity.UserItem;

/**
 * Author: thy
 * Date: 2022/3/17 10:23
 */
public interface UserService {
    /**
     * 验证用户信息
     * */
    ResultBody<UserItem> checkUser(String username, String password);

    /**
     * 新增用户
     * */
    ResultBody<UserItem> addUser(UserItem userItem);

    /**
     * 发送邮件重置密码
     * */
    ResultBody<UserItem> sendMail(String email);
}
