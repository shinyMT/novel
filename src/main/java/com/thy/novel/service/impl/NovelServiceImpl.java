package com.thy.novel.service.impl;

import com.thy.novel.dao.NovelDao;
import com.thy.novel.entity.ErrorCode;
import com.thy.novel.entity.NovelItem;
import com.thy.novel.entity.ResponseItem;
import com.thy.novel.entity.UserBooksItem;
import com.thy.novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter, int useId, String bookName, String bookAuthor) {
        ResponseItem<NovelItem> item = new ResponseItem<>();
        System.out.println("--------- 开始获取 ---------");
        try {
            // 定义一个变量作为服务器存放爬取成功后的目录
            String targetPath = "D:\\";
            // 第一个参数是Python的默认环境，可通过地址指定为特定环境
            // 第二个参数是要执行的Python文件，剩下的参数是要传递给Python的参数
            String[] pyFile = new String[]{"G:\\AppData\\Local\\Programs\\Python\\Python38\\python",
                    "D:\\project\\thy\\python\\novel\\novel.py", String.valueOf(totalChapter), targetPath};
            // 执行Python文件
            Process proc = Runtime.getRuntime().exec(pyFile);

            // 用输入流来截取执行Python过程中打印的结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GB2312"));
            String line;
            while ((line = in.readLine()) != null){
                System.out.println(line);
            }
            item.setCode(ErrorCode.SUCCESS);
            item.setMsg("获取成功");
            // 释放资源
            in.close();
            proc.waitFor();

            // 拼接爬取的成功的小说地址
            String novelPath = targetPath + bookName + ".txt";
            // 爬取成功后将相应的信息添加到数据库中
            novelDao.addNovelInfo(useId, "", bookName, bookAuthor, novelPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            item.setCode(ErrorCode.UNKNOWN);
            item.setMsg("获取失败");
        }

        System.out.println("--------- 获取结束 ---------");
        return item;
    }

    @Override
    public ResponseItem<UserBooksItem> getNovelListByUserId(int userId) {
        ResponseItem<UserBooksItem> item = new ResponseItem<>();
        List<UserBooksItem> bookList = novelDao.getNovelListByUserId(userId);
        if(bookList != null){
            item.setCode(ErrorCode.SUCCESS);
            item.setMsg("获取成功");
            item.setData(bookList);
        }else{
            item.setCode(ErrorCode.UNKNOWN);
            item.setMsg("获取失败");
        }

        return item;
    }
}
