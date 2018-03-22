package com.ltg263.happy_dzh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.adapter.NewsDetailsAdapter;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.NewsDetailsBean;
import com.ltg263.happy_dzh.utils.IntentUtil;
import com.ltg263.happy_dzh.utils.LogUtil;
import com.ltg263.happy_dzh.utils.StringUtil;
import com.ltg263.happy_dzh.view.Dialog_Exit;

import java.util.List;

import butterknife.BindView;

import static com.ltg263.happy_dzh.fragment.FirstFragment_1.NEWS_TITLE;

/**
 * 作者： litongge
 * 时间：  2017/2/26 14:50
 * 邮箱；ltg263@126.com
 * 描述：实时新闻详情
 */

public class NewsActivity extends BaseActivity {
    @BindView(R.id.lv_news)
    ListView lvNews;
    private String newsTitle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<NewsDetailsBean.ResultBean> result;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_news;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        Intent intent = getIntent();
        newsTitle = intent.getStringExtra(NEWS_TITLE);
        if(StringUtil.isBlank(newsTitle)){
            newsTitle = intent.getStringExtra("word");
        }
        toolbar.setTitle("相关"+newsTitle+"新闻列表");
        toolbar.setNavigationIcon(R.drawable.action_bar_return);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light_bule));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        isLoading = true;
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(result!=null){
                    String[] keys = {"url",NEWS_TITLE};
                    String[] values = {result.get(position).getUrl(),result.get(position).getTitle()};
                    IntentUtil.startActivity(WebViewActivity.class,keys,values);
                }
            }
        });
    }

    @Override
    protected void initData() {
        RxHttpParams params = new RxHttpParams.Build()
                .addQuery("q", newsTitle)
                .url(Constants.QUERY_NEWS)
                .build();
        RetrofitUtil.request(params, NewsDetailsBean.class, new RetrofitUtil.HttpCallBackImpl<NewsDetailsBean>() {
            @Override
            public void onCompleted(NewsDetailsBean detailsBean) {
                if(detailsBean.getError_code()==0){
                    result = detailsBean.getResult();
                    lvNews.setAdapter(new NewsDetailsAdapter(NewsActivity.this,result));
                }else{
                    Dialog_Exit.showDialogOneBtn(NewsActivity.this, detailsBean.getReason(), new Dialog_Exit.DialogConfirm() {
                        @Override
                        public void confirm() {
                            onBackPressed();
                        }
                    });
                }

                if(loadingDoalog.isShowing()){
                    loadingDoalog.dismiss();
                }
            }

            @Override
            public void onError(String message) {
                LogUtil.w("==========================:"+message);
                super.onError(message);
            }
        });
    }
}
