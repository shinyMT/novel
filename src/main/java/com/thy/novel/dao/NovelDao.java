package com.thy.novel.dao;

import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.sql.NovelSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Author: thy
 * Date: 2022/3/14 14:22
 */
@Mapper
public interface NovelDao {
    /**
     * 添加书籍信息
     * */
    @InsertProvider(type = NovelSql.class, method = "addNovelInfo")
    int addNovelInfo(int userId, String bookImage, String bookName, String bookAuthor, String bookPath);

    /**
     * 根据用户id获取其对应的所有书籍
     * */
    @SelectProvider(type = NovelSql.class, method = "getNovelListByUserId")
    List<UserBooksItem> getNovelListByUserId(int userId);
}
