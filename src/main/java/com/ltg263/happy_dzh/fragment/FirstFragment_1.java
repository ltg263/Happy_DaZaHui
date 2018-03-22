package com.ltg263.happy_dzh.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.NewsActivity;
import com.ltg263.happy_dzh.adapter.base.BaseListAdapter;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.NewsBean;
import com.ltg263.happy_dzh.fragment.base.BaseFragment;
import com.ltg263.happy_dzh.utils.IntentUtil;

import java.util.List;

import butterknife.BindView;


/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：实时新闻列表
 */

public class FirstFragment_1 extends BaseFragment {
    @BindView(R.id.lv_news)
    ListView lvNews;
    private List<String> result;
    public static final String NEWS_TITLE = "news_title";

    @Override
    public int setOnCreateView() {
        return R.layout.fragment_first_1;
    }

    @Override
    protected void initUI() {
        loadView(true);
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(result!=null){
                    IntentUtil.startActivityStr(NewsActivity.class,NEWS_TITLE,result.get(position));
                }
            }
        });
    }

    @Override
    protected void loadData() {
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.WORDS)
                .build();
        RetrofitUtil.request(params, NewsBean.class, new RetrofitUtil.HttpCallBackImpl<NewsBean>() {
            @Override
            public void onCompleted(NewsBean newsBean) {
                result = newsBean.getResult();
                lvNews.setAdapter(new NewsAdapter(mContext,result));
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }
    class NewsAdapter extends BaseListAdapter<String>{

        public NewsAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tvNewsContent;
            if(convertView==null){
                convertView =mInflater.inflate(R.layout.item_news_content,null);
                tvNewsContent = (TextView) convertView.findViewById(R.id.tv_news_content);
                convertView.setTag(tvNewsContent);
            }else{
                tvNewsContent= (TextView) convertView.getTag();
            }
            tvNewsContent.setText(mData.get(position));
            return convertView;
        }
    }
}