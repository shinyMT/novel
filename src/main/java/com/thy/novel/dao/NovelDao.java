package com.thy.novel.dao;

import com.thy.novel.entity.BookProgressItem;
import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.sql.NovelSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

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

    /**
     * 根据作者名和书名获取书籍信息
     * */
    @SelectProvider(type = NovelSql.class, method = "getNovelByNameAndAuthor")
    UserBooksItem getNovelByNameAndAuthor(String name, String author);

    /**
     *  新增阅读进度
     * */
    @InsertProvider(type = NovelSql.class, method = "addProgressByUserId")
    int addProgressByUserId(int userId, int bookId, String progress);

    /**
     * 更新阅读进度
     * */
    @UpdateProvider(type = NovelSql.class, method = "updateProgress")
    int updateProgress(int userId, int bookId, String progress);

    /**
     * 根据用户ID和书籍ID获取阅读进度
     * */
    @SelectProvider(type = NovelSql.class, method = "getProgressById")
    BookProgressItem getProgressById(int userId, int bookId);
}
