package com.ltg263.happy_dzh.bean;

import com.ltg263.happy_dzh.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：实体类
 */
public class PinyinOrBiHuaBean extends BaseRes{

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * pinyin_key : A
         * pinyin : a
         */

        private String id;
        private String pinyin_key;
        private String bushou;
        private String bihua;
        private String pinyin;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPinyin_key() {
            return pinyin_key;
        }

        public void setPinyin_key(String pinyin_key) {
            this.pinyin_key = pinyin_key;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public void setBihua(String bihua) {
            this.bihua = bihua;
        }

        public String getBihua() {
            return bihua;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }

        public String getBushou() {
            return bushou;
        }
    }
}
