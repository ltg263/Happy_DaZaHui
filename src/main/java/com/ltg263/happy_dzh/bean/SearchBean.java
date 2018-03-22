package com.ltg263.happy_dzh.bean;

import com.ltg263.happy_dzh.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/3/5 11:03
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class SearchBean extends BaseRes{

    /**
     * result : {"list":[{"id":"931b59df4a1646bc","zi":"举","py":"ju","wubi":"iwfh","pinyin":"jǔ","bushou":"丶","bihua":"9"},{"id":"94d549af5a753da8","zi":"具","py":"ju","wubi":"hwu","pinyin":"jù","bushou":"八","bihua":"8"}],"page":1,"pagesize":2,"totalpage":78,"totalcount":155}
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
         * list : [{"id":"931b59df4a1646bc","zi":"举","py":"ju","wubi":"iwfh","pinyin":"jǔ","bushou":"丶","bihua":"9"},{"id":"94d549af5a753da8","zi":"具","py":"ju","wubi":"hwu","pinyin":"jù","bushou":"八","bihua":"8"}]
         * page : 1
         * pagesize : 2
         * totalpage : 78
         * totalcount : 155
         */

        private int page;
        private int pagesize;
        private int totalpage;
        private int totalcount;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 931b59df4a1646bc
             * zi : 举
             * py : ju
             * wubi : iwfh
             * pinyin : jǔ
             * bushou : 丶
             * bihua : 9
             */

            private String id;
            private String zi;
            private String py;
            private String wubi;
            private String pinyin;
            private String bushou;
            private String bihua;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZi() {
                return zi;
            }

            public void setZi(String zi) {
                this.zi = zi;
            }

            public String getPy() {
                return py;
            }

            public void setPy(String py) {
                this.py = py;
            }

            public String getWubi() {
                return wubi;
            }

            public void setWubi(String wubi) {
                this.wubi = wubi;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public String getBushou() {
                return bushou;
            }

            public void setBushou(String bushou) {
                this.bushou = bushou;
            }

            public String getBihua() {
                return bihua;
            }

            public void setBihua(String bihua) {
                this.bihua = bihua;
            }
        }
    }
}
