package com.thy.novel.controller;

import com.thy.base.result.ResultBody;
import com.thy.novel.entity.BookProgressItem;
import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.service.NovelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: thy
 * Date: 2022/3/14 14:29
 */
@RestController
@NoArgsConstructor
@Api(tags = {"书籍接口"})
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
    @ApiOperation(value = "书籍-获取", notes = "设置信息来爬取书籍")
    @PostMapping("/book/get")
    public ResultBody<NovelItem> ExecPythonScript(@ApiParam(value = "书籍信息", required = true) @RequestBody NovelItem novelItem,
                                                  @ApiParam(value = "用户id", required = true)
                                                    @RequestParam(value = "userId") int userId,
                                                  HttpServletRequest request){
        return novelService.ExecPythonScript(novelItem, userId, request);
    }

    /**
     * 获取当前用户所拥有的全部书籍
     * */
    @ApiOperation(value = "书籍-获取列表", notes = "获取当前用户名下的所有书籍信息")
    @PostMapping("/book/list")
    public ResultBody<List<UserBooksItem>> getNovelListByUserId(@ApiParam(value = "用户id", required = true)
                                                                    @RequestParam int userId){
        return novelService.getNovelListByUserId(userId);
    }

    /**
     * 添加阅读进度
     * */
    @ApiOperation(value = "书籍-设置进度", notes = "保存用户的阅读进度")
    @PostMapping("/add/progress")
    public ResultBody<String> addProgressByUserId(@ApiParam(value = "书籍进度", required = true)
                                                      @RequestBody BookProgressItem progress){
        return novelService.addProgressByUserId(progress);
    }

    /**
     * 获取当前阅读进度
     * */
    @ApiOperation(value = "书籍-获取进度", notes = "获取书籍对应的进度")
    @PostMapping("/get/progress")
    public ResultBody<BookProgressItem> getProgressById(@ApiParam(value = "书籍信息", required = true)
                                                            @RequestBody BookProgressItem bookInfo){
        return novelService.getProgressById(bookInfo);
    }
}
