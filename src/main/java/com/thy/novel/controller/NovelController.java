package com.thy.novel.controller;

import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
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

    @PostMapping("/book/add")
    public ResponseItem<NovelItem> addNovelInfo(String name, String url, String author){
        return novelService.addNovelInfo(name, url, author);
    }

    /**
     * 根据获取的章节数执行Python文件
     * */
    @PostMapping("/book/get")
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter){
        return novelService.ExecPythonScript(totalChapter);
    }
}
