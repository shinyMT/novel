package com.thy.novel.service;

import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserBooksItem;

/**
 * Author: thy
 * Date: 2022/3/14 14:26
 */
public interface NovelService {

    /**
     * 执行Python脚本
     * @param totalChapter 总章节数
     * */
    ResponseItem<NovelItem> ExecPythonScript(int totalChapter, int userId, String bookName,
                                             String bookAuthor, String bookUrl);

    /**
     * 根据用户id获取其对应的所有书籍
     * */
    ResponseItem<UserBooksItem> getNovelListByUserId(int userId);
}
