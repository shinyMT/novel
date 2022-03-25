package com.thy.novel.controller;

import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.service.NovelService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: thy
 * Date: 2022/3/14 14:29
 */
@RestController
@NoArgsConstructor
public class NovelController {
    private NovelService novelService;

    @Autowired
    public void setNovelService(NovelService novelService){
        this.novelService = novelService;
    }

    /**
     * 根据获取的章节数执行Python文件
     * */
    @PostMapping("/book/get")
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter, int userId, String bookName,
                                                    String bookAuthor, String bookUrl){
        return novelService.ExecPythonScript(totalChapter, userId, bookName, bookAuthor, bookUrl);
    }

    /**
     * 获取当前用户所拥有的全部书籍
     * */
    @PostMapping("/book/list")
    public ResponseItem<UserBooksItem> getNovelListByUserId(int userId){
        return novelService.getNovelListByUserId(userId);
    }
}
