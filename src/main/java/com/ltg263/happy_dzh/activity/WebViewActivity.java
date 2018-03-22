package com.ltg263.happy_dzh.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;

import butterknife.BindView;

import static com.ltg263.happy_dzh.fragment.FirstFragment_1.NEWS_TITLE;

/**
 * 作者： litongge
 * 时间： 2017/4/7 16:16
 * 邮箱；ltg263@126.com
 * 描述：网页
 */
public class WebViewActivity extends BaseActivity {
    String url = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview_webview)
    WebView webView;
    private ProgressBar progressBar;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("url")) {
            url = bundle.getString("url");
            toolbar.setTitle(bundle.getString(NEWS_TITLE));
        }
        toolbar.setNavigationIcon(R.drawable.action_bar_return);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light_bule));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        init();
        isLoading = false;
    }

    @Override
    protected void initData() {
        progressBar = (ProgressBar) findViewById(R.id.activity_web_progressbar);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void init() {
        WebSettings webSettings = webView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        // 设置Web视图
        webView.setWebViewClient(new webViewClient());
    }

    // Web视图
    private class webViewClient extends WebViewClient {

    }
}
