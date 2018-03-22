package com.ltg263.happy_dzh.bean;

import com.ltg263.happy_dzh.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/3/6 17:32
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class NewsBean extends BaseRes{

    private List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
