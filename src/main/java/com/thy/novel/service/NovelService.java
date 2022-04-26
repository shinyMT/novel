package com.thy.novel.service;

import com.thy.base.result.ResultBody;
import com.thy.novel.entity.BookProgressItem;
import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.UserBooksItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: thy
 * Date: 2022/3/14 14:26
 */
public interface NovelService {

    /**
     * 执行Python脚本
     * */
    ResultBody<NovelItem> ExecPythonScript(NovelItem novelItem, int userId, HttpServletRequest request);

    /**
     * 根据用户id获取其对应的所有书籍
     * */
    ResultBody<List<UserBooksItem>> getNovelListByUserId(int userId);

    /**
     * 测试拼音工具
     * */
    void test();

    /**
     * 新增阅读进度
     * */
    ResultBody<String> addProgressByUserId(BookProgressItem progress);

    /**
     * 根据用户ID和书籍ID获取阅读进度
     * */
    ResultBody<BookProgressItem> getProgressById(BookProgressItem bookInfo);
}
