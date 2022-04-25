package com.thy.novel.service.impl;

import com.thy.base.result.ErrorCode;
import com.thy.base.result.ErrorInfo;
import com.thy.base.result.ResultBody;
import com.thy.novel.dao.NovelDao;
import com.thy.novel.entity.*;
import com.thy.novel.service.NovelService;
import com.thy.novel.util.PinYinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: thy
 * Date: 2022/3/14 14:27
 */
@Service
public class NovelServiceImpl implements NovelService {
    private NovelDao novelDao;
    private PinYinUtil pinYinUtil;
    // 保存的目标路径
    @Value("${python.target-path}")
    private String targetPath;
    // 待执行文件的路径
    @Value("${python.exe-file}")
    private String exeFile;
    // python3的执行环境
    @Value("${python.source-py-path}")
    private String sourcePyPath;

    @Autowired
    public void setNovelDao(NovelDao novelDao){
        this.novelDao = novelDao;
    }
    @Autowired
    public void setPinYinUtil(PinYinUtil pinYinUtil){
        this.pinYinUtil = pinYinUtil;
    }

    @Override
    public ResultBody<NovelItem> ExecPythonScript(NovelItem novelItem, int userId, HttpServletRequest request) {
        ErrorInfo errorInfo;
        boolean checkResult = checkNovelIsExist(novelItem.getName(), novelItem.getAuthor());
        if(checkResult){
            // 如果数据库存在同名小说，则直接返回，不执行python
            errorInfo = new ErrorInfo(ErrorCode.UNKNOWN, "书籍信息已经存在");
        }else {
            System.out.println("--------- 开始获取 ---------");
            try {
                // 第一个参数是Python的默认环境，可通过地址指定为特定环境
                // 第二个参数是要执行的Python文件，剩下的参数是要传递给Python的参数
                String[] pyFile = new String[]{sourcePyPath, exeFile, String.valueOf(novelItem.getTotalChapter()),
                        targetPath, novelItem.getUrl(), novelItem.getName()};
                // 执行Python文件
                Process proc = Runtime.getRuntime().exec(pyFile);

                // 用输入流来截取执行Python过程中打印的结果
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GB2312"));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                errorInfo = new ErrorInfo(ErrorCode.SUCCESS, "获取成功");
                // 释放资源
                in.close();
                proc.waitFor();

                // 将小说的名字转换为拼音
                String namePinYin = pinYinUtil.toPinYin(novelItem.getName());
                // 拼接爬取的成功的小说地址
                String novelPath = targetPath.replace("\\", "\\\\") + namePinYin + ".epub";
                // 爬取成功后将相应的信息添加到数据库中
                novelDao.addNovelInfo(userId, "", novelItem.getName(), novelItem.getAuthor(), novelPath);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                errorInfo = new ErrorInfo(ErrorCode.UNKNOWN, "获取失败");
            }
            System.out.println("--------- 获取结束 ---------");
        }

        return new ResultBody<>(errorInfo);
    }

    @Override
    public ResultBody<List<UserBooksItem>> getNovelListByUserId(int userId) {
        List<UserBooksItem> bookList = novelDao.getNovelListByUserId(userId);
        if(bookList != null){
            return new ResultBody<>(bookList);
        }else{
            return new ResultBody<>(new ErrorInfo(ErrorCode.UNKNOWN, "获取失败"));
        }
    }

    @Override
    public void test() {
        String result = pinYinUtil.toPinYin("离谱");
        System.out.println(result);
    }

    @Override
    public ResultBody<String> addProgressByUserId(int userId, int bookId, String progress) {
        ErrorInfo info;
        // 添加之前先从数据库获取进度信息
        BookProgressItem bookProgressItem = novelDao.getProgressById(userId, bookId);
        if(bookProgressItem != null){
            // 不为空说明进度已经存在，只需要更新进度即可
            int updateResult = novelDao.updateProgress(userId, bookId, progress);
            if(updateResult > 0){
                info = new ErrorInfo(ErrorCode.SUCCESS, "更新进度成功");
            }else {
                info = new ErrorInfo(ErrorCode.UNKNOWN, "更新进度失败");
            }
        }else{
            // 如果为空则直接新增
            int result = novelDao.addProgressByUserId(userId, bookId, progress);
            if(result > 0){
                info = new ErrorInfo(ErrorCode.SUCCESS, "添加成功");
            }else{
                info = new ErrorInfo(ErrorCode.UNKNOWN, "添加失败");
            }
        }

        return new ResultBody<>(info);
    }

    @Override
    public ResultBody<BookProgressItem> getProgressById(int userId, int bookId) {
        BookProgressItem bookProgressItem = novelDao.getProgressById(userId, bookId);
        if(bookProgressItem != null){
            return new ResultBody<>(bookProgressItem);
        }else {
            return new ResultBody<>(new ErrorInfo(ErrorCode.UNKNOWN, "获取失败"));
        }
    }

    /**
     * 封装一个判断数据库中是否存在同名小说的方法
     * */
    private boolean checkNovelIsExist(String name, String author){
        UserBooksItem item = novelDao.getNovelByNameAndAuthor(name, author);
        // 不为空，则说明数据库中已存在同名书籍，否则返回false
        return item != null;
    }
}
