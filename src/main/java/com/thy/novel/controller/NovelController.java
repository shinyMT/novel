package com.thy.novel.controller;

import com.thy.novel.entity.BookProgressItem;
import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.service.NovelService;
import com.thy.novel.util.PinYinUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * 测试拼音工具
     * */
    @GetMapping("/test")
    private void testPinyin(){
        novelService.test();
    }

    /**
     * 根据获取的章节数执行Python文件
     * */
    @PostMapping("/book/get")
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter, int userId, String bookName,
                                                    String bookAuthor, String bookUrl, HttpServletRequest request){
        return novelService.ExecPythonScript(totalChapter, userId, bookName, bookAuthor, bookUrl, request);
    }

    /**
     * 获取当前用户所拥有的全部书籍
     * */
    @PostMapping("/book/list")
    public ResponseItem<UserBooksItem> getNovelListByUserId(int userId){
        return novelService.getNovelListByUserId(userId);
    }

    /**
     * 添加阅读进度
     * */
    @PostMapping("/add/progress")
    public ResponseItem<String> addProgressByUserId(int userId, int bookId, String progress){
        return novelService.addProgressByUserId(userId, bookId, progress);
    }

    /**
     * 获取当前阅读进度
     * */
    @PostMapping("/get/progress")
    public ResponseItem<BookProgressItem> getProgressById(int userId, int bookId){
        return novelService.getProgressById(userId, bookId);
    }
}
