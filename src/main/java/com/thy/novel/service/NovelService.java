package com.thy.novel.service;

import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;

/**
 * Author: thy
 * Date: 2022/3/14 14:26
 */
public interface NovelService {
    /**
     * 添加书籍信息
     * */
    ResponseItem<NovelItem> addNovelInfo(String name, String url, String author);

    /**
     * 执行Python脚本
     * @param totalChapter 总章节数
     * */
    ResponseItem<NovelItem> ExecPythonScript(int totalChapter);
}
