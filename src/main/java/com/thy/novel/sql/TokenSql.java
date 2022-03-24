package com.thy.novel.sql;

/**
 * Author: thy
 * Date: 2022/3/23 16:41
 */
public class TokenSql {
    // 表名
    private static final String TOKEN_TABLE = "token";

    /**
     * 添加token
     * @param userId 用户ID
     * @param expireTime token到期时间
     * @param token token值
     * */
    public String addToken(int userId, String expireTime, String token){
        return "insert into " + TOKEN_TABLE + "(userId, expireTime, token) values"
                + "(#{userId}, #{expireTime}, #{token})";
    }

    /**
     * 删除token
     * @param id tokenID
     * */
    public String delToken(int id){
        return "delete from " + TOKEN_TABLE + "where id=" + id;
    }

}
