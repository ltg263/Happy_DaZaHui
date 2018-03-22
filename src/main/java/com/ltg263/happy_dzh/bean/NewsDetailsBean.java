package com.ltg263.happy_dzh.bean;

import com.ltg263.happy_dzh.bean.base.BaseRes;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/3/6 17:32
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class NewsDetailsBean extends BaseRes{

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 男子一人分饰244角 担任角色涵盖四海八荒!
         * content : <em>一人分饰244角一人分饰244角</em>是怎么回事? & nbsp3月 6日消息,如果你是一个热衷于追寻知识的人,相信知乎上应该有你的足迹。不过在互联网上面对一群人向你灌输知识,你是否有过疑问,别人分享的知识和经验都是靠谱的吗?恐怕未必…例如,最近知乎上就有一名答主火了,他叫" 海贼- 王...
         * img_width :
         * full_title : 男子一人分饰244角 担任角色涵盖四海八荒!
         * pdate : 43分钟前
         * src : 东方网
         * img_length :
         * img :
         * url : http://news.eastday.com/eastday/13news/auto/news/world/20170307/u7ai6570492.html
         * pdate_src : 2017-03-07 10:58:00
         */

        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_width() {
            return img_width;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getImg_length() {
            return img_length;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }
    }
}
