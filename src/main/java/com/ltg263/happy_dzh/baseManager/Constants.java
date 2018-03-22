package com.ltg263.happy_dzh.baseManager;


/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：常用的常量
 */
public interface Constants {
    /**
     * 是否开启调试模式
     */
    boolean DEBUG = true;
    String URL = "http://v.juhe.cn/";
    //新华字典
    String XHZD_RRL =URL+"xhzd/";
    String PINYIN = XHZD_RRL+"pinyin";
    String BUSHOU = XHZD_RRL+"bushou";
    String QUERY = XHZD_RRL+"query";
    String QUERY_PY = XHZD_RRL+"querypy";
    String QUERY_BS = XHZD_RRL+"querybs";
    //电影订票
    String DYPDY_RRL =URL+"wepiao/query";
    //实时新闻
    String NEWS_URL = "http://op.juhe.cn/onebox/news/";
    String WORDS = NEWS_URL + "words";
    String QUERY_NEWS = NEWS_URL + "query";

}
