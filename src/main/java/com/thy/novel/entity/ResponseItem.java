package com.thy.novel.entity;

import java.io.Serializable;

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
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
