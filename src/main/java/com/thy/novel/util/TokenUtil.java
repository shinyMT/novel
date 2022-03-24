package com.thy.novel.util;

import java.util.Calendar;

/**
 * Author: thy
 * Date: 2022/3/23 16:51
 * 生成token的工具类
 */
public class TokenUtil {
    public String generateToken(int userId){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DATE));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));

        // 将时间和用户id拼接

        return userId + "thy" + year + month + day + hour + minute + second;
    }
}
