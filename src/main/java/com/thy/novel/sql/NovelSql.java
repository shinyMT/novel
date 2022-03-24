package com.thy.novel.sql;

/**
 * Author: thy
 * Date: 2022/3/14 14:19
 */
public class NovelSql {
    // 书籍表
    private static final String NOVEL_TABLE = "novelInfo";
    // 用户对应的书籍表
    private static final String USER_NOVEL_TABLE = "userBooks";

    /**
     * 向数据库中添加书籍信息
     * */
    public String addNovelInfo(int userId, String bookImage, String bookName, String bookAuthor, String bookPath){
        return "insert into " + USER_NOVEL_TABLE + "(userId, bookImage, bookName, bookAuthor, bookPath) " +
                "values(" + userId
                + "'" + bookImage + "'"
                + "'" + bookName + "'"
                + "'" + bookAuthor + "'"
                + "'" + bookPath + "')";
    }

    /**
     * 获取当前用户所拥有的全部书籍
     * */
    public String getNovelListByUserId(int userId){
        return "select * from " + USER_NOVEL_TABLE + " where userId=" + userId;
    }

}
