package com.ltg263.happy_dzh.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.MovieTicketBean;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2017/3/6 16:11
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MovieTicketActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_movie_ticket;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbar.setTitle("电影票订阅");
        toolbar.setNavigationIcon(R.drawable.action_bar_return);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light_bule));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DYPDY_RRL)
                .build();
        RetrofitUtil.request(params, MovieTicketBean.class, new RetrofitUtil.HttpCallBackImpl<MovieTicketBean>() {
            @Override
            public void onCompleted(MovieTicketBean ticketBean) {
                MovieTicketBean.ResultBean a = (MovieTicketBean.ResultBean) ticketBean.getResult();
                webview.loadUrl(ticketBean.getResult().getH5url());
            }
        });

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }

    /**
     *  设置回退覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void initData() {

    }
}
