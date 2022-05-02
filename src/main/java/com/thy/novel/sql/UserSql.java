package com.thy.novel.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * Author: thy
 * Date: 2022/3/17 10:13
 */
public class UserSql {
    // 表名
    private static final String USER_TABLE = "user";

    /**
     * 验证账号信息
     * */
    public String checkUser(){
        SQL sql = new SQL();
        sql.SELECT("*")
                .FROM(USER_TABLE)
                .WHERE("name=#{name}");

        return sql.toString();
    }

    /**
     * 新增用户
     * */
    public String addUser(){
        SQL sql = new SQL();
        sql.INSERT_INTO(USER_TABLE)
                .VALUES("name", "#{name}")
                .VALUES("password", "#{password}")
                .VALUES("email", "#{email}");

        return sql.toString();
    }

    /**
     * 重置用户密码
     * */
    public String resetPwd(String email){
        return "update user set password=" + "'123456' where email='" + email + "'";
    }
}
