package com.thy.novel.dao;

import com.thy.novel.sql.NovelSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

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
    int addNovelInfo(String name, String url, String author);
}
