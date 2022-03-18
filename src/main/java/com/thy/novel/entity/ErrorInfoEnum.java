package com.thy.novel.entity;

/**
 * Author: thy
 * Date: 2022/3/17 10:41
 */
public enum ErrorInfoEnum {
    SUCCESS(ErrorCode.SUCCESS, "success"),
    UNKNOWN(ErrorCode.UNKNOWN, "unknown");

    private int code;
    private String msg;

    ErrorInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
