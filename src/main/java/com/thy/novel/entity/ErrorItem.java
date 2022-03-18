package com.thy.novel.entity;

import java.io.Serializable;

/**
 * Author: thy
 * Date: 2022/3/17 10:38
 */
public class ErrorItem implements Serializable {
    private static final long serialVersionUID = -3020672189993545199L;

    // 错误代码
    private int code;
    // 错误信息
    private String msg;

    public ErrorItem(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
