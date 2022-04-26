package com.thy.novel.service.impl;

import com.thy.base.result.ErrorCode;
import com.thy.base.result.ErrorInfo;
import com.thy.base.result.GlobalException;
import com.thy.base.result.ResultBody;
import com.thy.novel.dao.UserDao;
import com.thy.novel.entity.UserItem;
import com.thy.novel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * Author: thy
 * Date: 2022/3/17 10:24
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private JavaMailSender mailSender;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    public ResultBody<UserItem> checkUser(UserItem loginInfo) {
        UserItem userItem = userDao.checkUser(loginInfo.getName());
        ErrorInfo errorInfo;
        if(userItem != null){
            String pwd = userItem.getPassword();
            if(pwd.equals(loginInfo.getPassword())){
                // 验证成功
                errorInfo = new ErrorInfo(ErrorCode.SUCCESS, "验证成功");
            }else{
                // 验证失败
                errorInfo = new ErrorInfo(ErrorCode.LOGIN_PW_ERR, "账号密码错误");
            }
        }else{
            // 用户不存在
            errorInfo = new ErrorInfo(ErrorCode.LOGIN_ACCOUNT_DISABLED, "账号不存在");
        }

        return new ResultBody<>(errorInfo);
    }

    @Override
    public ResultBody<UserItem> addUser(UserItem userItem) {
        UserItem user = userDao.checkUser(userItem.getName());
        ErrorInfo errorInfo;
        if(user != null){
            errorInfo = new ErrorInfo(ErrorCode.LOGIN_ACCOUNT_DISABLED, "用户名已经存在");
        }else {
            int result = userDao.addUser(userItem.getName(), userItem.getPassword(), userItem.getEmail());
            if(result > 0){
                errorInfo = new ErrorInfo(ErrorCode.SUCCESS, "注册成功");
            }else {
                errorInfo = new ErrorInfo(ErrorCode.UNKNOWN, "注册失败");
            }
        }

        return new ResultBody<>(errorInfo);
    }

    @Override
    public ResultBody<UserItem> sendMail(String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teng25@foxmail.com");
        message.setTo(email);
        message.setSubject("重置密码");
        message.setText("重置后的密码为：123456");
        ErrorInfo errorInfo;
        try {
            mailSender.send(message);
            // 发送完毕后重置数据库中的密码
            int result = userDao.resetPwd(email);
            if(result > 0){
                errorInfo = new ErrorInfo(ErrorCode.SUCCESS, "重置成功");
            }else{
                errorInfo = new ErrorInfo(ErrorCode.UNKNOWN, "重置密码异常");
            }
        }catch (Exception e){
            errorInfo = new ErrorInfo(ErrorCode.LOGIN_EMAIL_ERROR, "邮件发送异常");
            System.out.println("邮件发送异常, " + e);
        }

        return new ResultBody<>(errorInfo);
    }
}
