package com.thy.novel.sql;

import org.apache.ibatis.jdbc.SQL;

import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

/**
 * Author: thy
 * Date: 2022/3/14 14:19
 */
public class NovelSql {
    // 书籍表
    private static final String NOVEL_TABLE = "novelInfo";
    // 用户对应的书籍表
    private static final String USER_NOVEL_TABLE = "userBooks";
    // 书籍的阅读进度表
    private static final String BOOK_PROGRESS_TABLE = "bookProgress";

    /**
     * 向数据库中添加书籍信息
     * */
    public String addNovelInfo(int userId, String bookImage, String bookName, String bookAuthor, String bookPath){
        return "insert into " + USER_NOVEL_TABLE + "(userId, bookImage, bookName, bookAuthor, bookPath) " +
                "values(" + userId
                + ",'" + bookImage + "'"
                + ",'" + bookName + "'"
                + ",'" + bookAuthor + "'"
                + ",'" + bookPath + "')";
    }

    /**
     * 获取当前用户所拥有的全部书籍
     * */
    public String getNovelListByUserId(){
        SQL sql = new SQL();
        sql.SELECT("*")
                .FROM(USER_NOVEL_TABLE)
                .WHERE("userId=#{userId}");

        return sql.toString();
    }

    /**
     * 根据书名和作者名获取书籍信息
     * */
    public String getNovelByNameAndAuthor(String name, String author){
        return "select * from " + USER_NOVEL_TABLE + " where bookName='" + name
                + "' and bookAuthor='" + author + "'";
    }

    /**
     * 根据用户ID添加阅读进度
     * @param userId 用户ID
     * @param bookId userBooks中书籍对应的ID
     * @param progress 前端获取的阅读进度
     * */
    public String addProgressByUserId(int userId, int bookId, String progress){
        SQL sql = new SQL();
        sql.INSERT_INTO(BOOK_PROGRESS_TABLE);
        sql.VALUES("userId", "#{userId}")
        .VALUES("bookId", "#{bookId}")
        .VALUES("bookProgress", "#{progress}");

//        System.out.println(sql);
        return sql.toString();
    }

    /**
     * 更新阅读进度
     * */
    public String updateProgress(int userId, int bookId, String progress){
        SQL sql = new SQL();
        sql.UPDATE(BOOK_PROGRESS_TABLE);
        sql.SET("bookProgress=#{progress}");
        sql.WHERE("userId=#{userId} and bookId=#{bookId}");

        return sql.toString();
    }

    /**
     * 根据用户ID和书籍ID获取进度信息
     * */
    public String getProgressById(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM(BOOK_PROGRESS_TABLE);
        sql.WHERE("userId=#{userId} and bookID=#{bookId}");

        return sql.toString();
    }
}
