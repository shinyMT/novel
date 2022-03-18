package com.thy.novel.controller;

import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserItem;
import com.thy.novel.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: thy
 * Date: 2022/3/17 10:51
 */
@RestController
@NoArgsConstructor
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /**
     * 登录验证
     * */
    @PostMapping("/user/check")
    public ResponseItem<UserItem> checkUser(String username, String password){
        return userService.checkUser(username, password);
    }

    /**
     * 注册
     * */
    @PostMapping("/user/add")
    public ResponseItem<UserItem> addUser(String name, String password, String email){
        return userService.addUser(name, password, email);
    }

    /**
     * 忘记密码操作
     * */
    @PostMapping("/user/send")
    public ResponseItem<UserItem> sendMail(String email){
        return userService.sendMail(email);
    }
}
