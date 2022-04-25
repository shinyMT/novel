package com.thy.novel.entity;

import com.thy.base.BaseItem;


/**
 * Author: thy
 * Date: 2022/4/5 13:51
 * 书籍的阅读进度条目
 */
public class BookProgressItem extends BaseItem {
    private static final long serialVersionUID = -6544127365449799598L;

    private int id;
    // 用户ID
    private int userId;
    // 书籍ID
    private int bookId;
    // 阅读进度
    private String bookProgress;

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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookProgress() {
        return bookProgress;
    }

    public void setBookProgress(String bookProgress) {
        this.bookProgress = bookProgress;
    }
}
