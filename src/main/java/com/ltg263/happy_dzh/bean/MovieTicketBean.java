package com.ltg263.happy_dzh.bean;

import com.ltg263.happy_dzh.bean.base.BaseRes;

/**
 * 作者： litongge
 * 时间：  2017/3/6 16:20
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MovieTicketBean extends BaseRes{

    /**
     * result : {"h5url":"http://v.juhe.cn/wepiao/go?key=xxxxxx","h5weixin":"http://v.juhe.cn/wepiao/go?key=xxxxx&s=weixin"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * h5url : http://v.juhe.cn/wepiao/go?key=xxxxxx
         * h5weixin : http://v.juhe.cn/wepiao/go?key=xxxxx&s=weixin
         */

        private String h5url;
        private String h5weixin;

        public String getH5url() {
            return h5url;
        }

        public void setH5url(String h5url) {
            this.h5url = h5url;
        }

        public String getH5weixin() {
            return h5weixin;
        }

        public void setH5weixin(String h5weixin) {
            this.h5weixin = h5weixin;
        }
    }
}
