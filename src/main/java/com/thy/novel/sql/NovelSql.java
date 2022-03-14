package com.thy.novel.sql;

/**
 * Author: thy
 * Date: 2022/3/14 14:19
 */
public class NovelSql {
    // 表名
    private static final String NOVEL_TABLE = "novelInfo";

    /**
     * 向数据库中添加书籍信息
     * */
    public String addNovelInfo(String name, String url, String author){
        return "insert into " + NOVEL_TABLE + "(name, url, author) " +
                "values('" + name +"', '" + url + "', '" + author + "')";
    }

}
