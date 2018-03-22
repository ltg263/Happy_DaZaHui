package com.ltg263.happy_dzh.fragment.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ltg263.happy_dzh.utils.LogUtil;
import com.ltg263.happy_dzh.view.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：所有Fragment的父类
 */

public abstract class BaseFragment extends Fragment {
 /*   public ImageOptions options = new ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565)
            .setImageScaleType(ImageView.ScaleType.FIT_CENTER).build();*/
    protected View mView;
    protected Activity mContext;
    /**
     * 是否需要使用loadData每次加载
     */
    protected boolean isLoadData = false;
    protected LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setOnCreateView(), container, false);
        ButterKnife.bind(this, mView);//绑定framgent
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initUI();
    }

        @Override
    public void onResume() {
        super.onResume();
        if (isLoadData) {
            loadData();
        }
    }

    protected void loadView(boolean isDialog) {
        if(isDialog){
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(mContext);
            }
            loadingDialog.show();
        }
        loadData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public abstract int setOnCreateView();

    protected abstract void initUI();

    protected abstract void loadData();

}
