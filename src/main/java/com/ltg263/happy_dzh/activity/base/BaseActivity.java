package com.ltg263.happy_dzh.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.view.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：所有Activity的父类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected boolean isLoading = false;
    protected LoadingDialog loadingDoalog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setOnCreate());
        initUI(savedInstanceState);
        isLoadingDoalog();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        //转场动画
        overridePendingTransition(R.anim.fade_in_right, R.anim.fade_out_right);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish  动画
        overridePendingTransition(R.anim.fade_in_left, R.anim.fade_out_left);
        this.finish();
    }
    private void isLoadingDoalog() {
        if(isLoading){
            if(loadingDoalog==null){
                loadingDoalog = new LoadingDialog(this);
            }
            loadingDoalog.show();
        }
        initData();
    }
    /**
     * 重写onCreate
     */
    protected abstract int setOnCreate();
    /**
     * 初始化控件
     */
    protected abstract void initUI(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initData();


}
