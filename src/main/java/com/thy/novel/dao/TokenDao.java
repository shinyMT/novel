package com.thy.novel.dao;

import com.thy.novel.sql.TokenSql;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: thy
 * Date: 2022/3/23 17:21
 */
@Mapper
public interface TokenDao {
    /**
     * 添加token
     * @param userId 用户ID
     * @param expireTime token到期时间
     * @param token token值
     * */
    @InsertProvider(type = TokenSql.class, method = "addToken")
    int addToken(int userId, String expireTime, String token);

    /**
     * 删除token
     * @param id tokenID
     * */
    @DeleteProvider(type = TokenSql.class, method = "delToken")
    Object delToken(int id);
}
