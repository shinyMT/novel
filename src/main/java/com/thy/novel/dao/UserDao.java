package com.thy.novel.dao;

import com.thy.novel.entity.UserItem;
import com.thy.novel.sql.UserSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Author: thy
 * Date: 2022/3/17 10:20
 */
@Mapper
public interface UserDao {
    /**
     * 验证用户信息
     * */
    @SelectProvider(type = UserSql.class, method = "checkUser")
    UserItem checkUser(String username);

    /**
     * 新增用户
     * */
    @InsertProvider(type = UserSql.class, method = "addUser")
    int addUser(String name, String password, String email);

    /**
     * 重置密码
     * */
    @UpdateProvider(type = UserSql.class, method = "resetPwd")
    int resetPwd(String email);
}
