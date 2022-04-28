package com.thy.novel.controller;

import com.thy.base.result.ResultBody;
import com.thy.novel.entity.UserItem;
import com.thy.novel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = {"用户接口"})
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /**
     * 登录验证
     * */
    @ApiOperation(value = "用户-登录", notes = "用户登录验证")
    @PostMapping("/user/check")
    public ResultBody<UserItem> checkUser(@ApiParam(value = "用户登录信息", required = true) @RequestBody UserItem loginInfo){
        return userService.checkUser(loginInfo);
    }

    /**
     * 注册
     * */
    @ApiOperation(value = "用户-注册", notes = "添加一个新用户")
    @PostMapping("/user/add")
    public ResultBody<UserItem> addUser(@ApiParam(value = "新增用户信息", required = true) @RequestBody UserItem userItem){
        return userService.addUser(userItem);
    }

    /**
     * 忘记密码操作
     * */
    @ApiOperation(value = "用户-忘记密码", notes = "给邮箱发送重置后的密码")
    @PostMapping("/user/send")
    public ResultBody<UserItem> sendMail(@ApiParam(value = "邮箱账号", required = true) @RequestParam String email){
        return userService.sendMail(email);
    }
}
