package com.thy.novel.service.impl;

import com.thy.novel.dao.NovelDao;
import com.thy.novel.entity.*;
import com.thy.novel.service.NovelService;
import com.thy.novel.util.PinYinUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setNovelDao(NovelDao novelDao){
        this.novelDao = novelDao;
    }
    @Autowired
    public void setPinYinUtil(PinYinUtil pinYinUtil){
        this.pinYinUtil = pinYinUtil;
    }

    @Override
    public ResponseItem<NovelItem> ExecPythonScript(int totalChapter, int useId, String bookName,
                                                    String bookAuthor, String bookUrl, HttpServletRequest request) {
        ResponseItem<NovelItem> item = new ResponseItem<>();

        boolean checkResult = checkNovelIsExist(bookName, bookAuthor);
        if(checkResult){
            // 如果数据库存在同名小说，则直接返回，不执行python
            item.setCode(ErrorCode.FUNC_NO_EXECUTE_PYTHON);
            item.setMsg("该书籍信息已存在");
        }else {
            System.out.println("--------- 开始获取 ---------");
            try {
                // 定义一个变量作为服务器存放爬取成功后的目录
                String targetPath = "/Users/novel/";
//            String targetPath = "G:\\novel\\";
//            System.out.println("服务器路径：" + targetPath);
                // 第一个参数是Python的默认环境，可通过地址指定为特定环境
                // 第二个参数是要执行的Python文件，剩下的参数是要传递给Python的参数
//            String[] pyFile = new String[]{"G:\\AppData\\Local\\Programs\\Python\\Python38\\python",
//                    "D:\\project\\thy\\python\\novel\\novel.py", String.valueOf(totalChapter),
//                    targetPath, bookUrl, bookName};
                String[] pyFile = new String[]{"python3",
                        "/Users/tenghaiyi/PycharmProjects/novel/novel.py", String.valueOf(totalChapter),
                        targetPath, bookUrl, bookName};
                // 执行Python文件
                Process proc = Runtime.getRuntime().exec(pyFile);

                // 用输入流来截取执行Python过程中打印的结果
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GB2312"));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("获取成功");
                // 释放资源
                in.close();
                proc.waitFor();

                // 将小说的名字转换为拼音
                String namePinYin = pinYinUtil.toPinYin(bookName);
                // 拼接爬取的成功的小说地址
                String novelPath = targetPath.replace("\\", "\\\\") + namePinYin + ".epub";
                // 爬取成功后将相应的信息添加到数据库中
                novelDao.addNovelInfo(useId, "", bookName, bookAuthor, novelPath);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                item.setCode(ErrorCode.UNKNOWN);
                item.setMsg("获取失败");
            }
            System.out.println("--------- 获取结束 ---------");
        }

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

    @Override
    public void test() {
        String result = pinYinUtil.toPinYin("离谱");
        System.out.println(result);
    }

    @Override
    public ResponseItem<String> addProgressByUserId(int userId, int bookId, String progress) {
        ResponseItem<String> item = new ResponseItem<>();
        // 添加之前先从数据库获取进度信息
        BookProgressItem bookProgressItem = novelDao.getProgressById(userId, bookId);
        if(bookProgressItem != null){
            // 不为空说明进度已经存在，只需要更新进度即可
            int updateResult = novelDao.updateProgress(userId, bookId, progress);
            if(updateResult > 0){
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("更新进度成功");
            }else {
                item.setCode(ErrorCode.UNKNOWN);
                item.setMsg("更新进度失败");
            }
        }else{
            // 如果为空则直接新增
            int result = novelDao.addProgressByUserId(userId, bookId, progress);
            if(result > 0){
                item.setCode(ErrorCode.SUCCESS);
                item.setMsg("添加成功");
            }else{
                item.setCode(ErrorCode.UNKNOWN);
                item.setMsg("添加失败");
            }
        }

        return item;
    }

    @Override
    public ResponseItem<BookProgressItem> getProgressById(int userId, int bookId) {
        ResponseItem<BookProgressItem> item = new ResponseItem<>();
        BookProgressItem bookProgressItem = novelDao.getProgressById(userId, bookId);
        List<BookProgressItem> list = new ArrayList<>();
        if(bookProgressItem != null){
            list.add(bookProgressItem);
            item.setCode(ErrorCode.SUCCESS);
            item.setMsg("获取成功");
            item.setData(list);
        }else {
            item.setCode(ErrorCode.UNKNOWN);
            item.setMsg("获取失败");
        }

        return item;
    }

    /**
     * 封装一个判断数据库中是否存在同名小说的方法
     * */
    private boolean checkNovelIsExist(String name, String author){
        UserBooksItem item = novelDao.getNovelByNameAndAuthor(name, author);
        if(item != null){
            // 不为空，则说明数据库中已存在同名书籍
            return true;
        }else{
            return false;
        }
    }
}
