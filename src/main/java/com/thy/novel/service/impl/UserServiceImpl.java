package com.thy.novel.service.impl;

import com.thy.novel.dao.UserDao;
import com.thy.novel.entity.ErrorCode;
import com.thy.novel.entity.ResponseItem;
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
    public ResponseItem<UserItem> checkUser(String username, String password) {
        ResponseItem<UserItem> item = new ResponseItem<>();
        UserItem user = userDao.checkUser(username);
        if(user != null){
            String pwd = user.getPassword();
            if(pwd.equals(password)){
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("验证成功");
            }else{
                item.setCode(ErrorCode.LOGIN_PW_ERROR);
                item.setMsg("密码不正确");
            }
        }else {
            item.setCode(ErrorCode.LOGIN_USER_NOT_EXIST);
            item.setMsg("用户不存在");
        }

        return item;
    }

    @Override
    public ResponseItem<UserItem> addUser(String name, String password, String email) {
        ResponseItem<UserItem> item = new ResponseItem<>();
        UserItem user = userDao.checkUser(name);
        if(user != null){
            item.setCode(ErrorCode.LOGIN_USER_EXIST);
            item.setMsg("用户名已经存在");
        }else {
            int result = userDao.addUser(name, password, email);
            if(result > 0){
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("注册成功");
            }else {
                item.setCode(ErrorCode.UNKNOWN);
                item.setMsg("注册失败");
            }
        }

        return item;
    }

    @Override
    public ResponseItem<UserItem> sendMail(String email) {
        ResponseItem<UserItem> item = new ResponseItem<>();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teng25@foxmail.com");
        message.setTo(email);
        message.setSubject("重置密码");
        message.setText("重置后的密码为：123456");
        try {
            mailSender.send(message);
            // 发送完毕后重置数据库中的密码
            int result = userDao.resetPwd(email);
            if(result > 0){
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("重置成功");
            }else{
                item.setCode(ErrorCode.UNKNOWN);
                item.setMsg("重置密码异常");
            }
        }catch (Exception e){
            item.setCode(ErrorCode.LOGIN_EMAIL_ERROR);
            item.setMsg("邮件发送异常");
            System.out.println("邮件发送异常, " + e);
        }

        return item;
    }
}
