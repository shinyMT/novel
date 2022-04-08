package com.thy.novel.entity;


/**
 * Author: thy
 * Date: 2022/3/17 10:28
 * 错误信息条目，正数为警告信息，负数为错误信息， 代码从00~99依次排序
 */
public class ErrorCode {

    private ErrorCode(){

    }

    // 成功
    public static final int SUCCESS = 0;

    // 未知错误
    public static final int UNKNOWN = -1;

    /**
     * ------------- APP 相关问题 -------------
     * */
    // APP版本过低
    public static final int APP_VERSION_LOW = -200;

    /**
     * ------------- 登录相关问题 -------------
     * */
    // 账号密码错误
    public static final int LOGIN_PW_ERROR = -300;
    // 用户不存在
    public static final int LOGIN_USER_NOT_EXIST = -301;
    // 用户存在
    public static final int LOGIN_USER_EXIST = -302;
    // 邮件发送异常
    public static final int LOGIN_EMAIL_ERROR = -303;


    /**
     * ------------- 功能模块问题 -------------
     * */
    // 功能未开启
    public static final int FUNC_NOT_OPEN = -500;
    // 无需执行python
    public static final int FUNC_NO_EXECUTE_PYTHON = -501;
}
