package com.thy.novel.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: thy
 * Date: 2022/3/14 14:18
 */
public class ResponseItem<T> implements Serializable {
    private static final long serialVersionUID = 5763509655814892015L;

    // 响应代码
    private int code;
    // 响应信息
    private String msg;
    // 响应数据
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
