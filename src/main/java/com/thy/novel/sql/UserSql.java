package com.thy.novel.sql;

/**
 * Author: thy
 * Date: 2022/3/17 10:13
 */
public class UserSql {
    // 表名
    private static final String USER_TABLE = "user";

    /**
     * 验证账号信息
     * @param username 用户名
     * */
    public String checkUser(String username){
        return "select password from " + USER_TABLE + " where name='" + username + "'";
    }

    /**
     * 新增用户
     * @param name 用户名
     * @param password 密码
     * @param email 邮箱
     * */
    public String addUser(String name, String password, String email){
        return "insert into user(name, password, email) values('" + name + "', '" + password + "', '" + email + "')";
    }

    /**
     * 重置用户密码
     * */
    public String resetPwd(String email){
        return "update user set password=" + "'123456' where email='" + email + "'";
    }
}
