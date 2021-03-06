package com.thy.novel.entity;

import com.thy.base.BaseItem;


/**
 * Author: thy
 * Date: 2022/3/14 14:11
 * 书籍信息条目
 */
public class NovelItem extends BaseItem {
    private static final long serialVersionUID = 3157296983134985968L;

    // 书名
    private String name;
    // 地址
    private String url;
    // 作者
    private String author;
    // 总章节数
    private int totalChapter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(int totalChapter) {
        this.totalChapter = totalChapter;
    }
}
