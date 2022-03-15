package com.thy.novel.service.impl;

import com.thy.novel.dao.NovelDao;
import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
import com.thy.novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: thy
 * Date: 2022/3/14 14:27
 */
@Service
public class NovelServiceImpl implements NovelService {
    private NovelDao novelDao;

    @Autowired
    public void setNovelDao(NovelDao novelDao){
        this.novelDao = novelDao;
    }


    @Override
    public ResponseItem<NovelItem> addNovelInfo(String name, String url, String author) {
        ResponseItem<NovelItem> item = new ResponseItem<>();
        int result = novelDao.addNovelInfo(name, url, author);
        if(result > 0){
            item.setCode(0);
            item.setMsg("添加成功");
        }else{
            item.setCode(-1);
            item.setMsg("添加失败");
        }

        return item;
    }

    @Override
    public int GetTotalChapter(int totalChapter) {
        return totalChapter;
    }

    @Override
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter) {
        ResponseItem<NovelItem> item = new ResponseItem<>();
        System.out.println("开始获取...");
        try {
            // 第一个参数是Python的默认环境，可通过地址指定为特定环境
            // 第二个参数是要执行的Python文件，第三个参数是传递的参数
            String[] pyFile = new String[]{"G:\\AppData\\Local\\Programs\\Python\\Python38\\python",
                    "D:\\project\\thy\\python\\novel\\novel.py", String.valueOf(totalChapter)};
            // 执行Python文件
            Process proc = Runtime.getRuntime().exec(pyFile);

            // 用输入流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GB2312"));
            String line;
            while ((line = in.readLine()) != null){
                System.out.println(line);
            }
            item.setCode(0);
            item.setMsg("获取成功");
            // 释放资源
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            item.setCode(-1);
            item.setMsg("获取失败");
        }

        System.out.println("获取结束");
        return item;
    }
}
