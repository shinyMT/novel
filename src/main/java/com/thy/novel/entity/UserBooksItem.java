package com.thy.novel.entity;

import java.io.Serializable;

/**
 * Author: thy
 * Date: 2022/3/24 14:54
 * 用户对应的书籍条目
 */
public class UserBooksItem implements Serializable {
    private static final long serialVersionUID = -3660179815143647047L;

    // id
    private int id;
    // 用户ID
    private int userId;
    // 书籍图片
    private String bookImages;
    // 书籍名
    private String bookName;
    // 书籍作者
    private String bookAuthor;
    // 书籍爬取后在服务器上的地址
    private String bookPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBookImages() {
        return bookImages;
    }

    public void setBookImages(String bookImages) {
        this.bookImages = bookImages;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }
}
