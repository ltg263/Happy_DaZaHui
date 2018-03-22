package com.ltg263.happy_dzh.bean.base;

/**
 * 作者： litongge
 * 时间：  2017/3/2 16:05
 * 邮箱；ltg263@126.com
 */

public class BaseRes extends BaseInfo{
    public String reason;
    public int error_code;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
