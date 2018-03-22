package com.ltg263.happy_dzh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.HanZiQuery;
import com.ltg263.happy_dzh.utils.LogUtil;
import com.ltg263.happy_dzh.utils.ScreenUtil;
import com.ltg263.happy_dzh.utils.StringUtil;
import com.ltg263.happy_dzh.view.Dialog_Exit;
import com.yinglan.scrolllayout.ScrollLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间：  2017/3/3 23:08
 * 邮箱；ltg263@126.com
 * 描述：字典详情页
 */

public class HanZiDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_han_zi)
    TextView tvHanZi;
    @BindView(R.id.tv_zi)
    TextView tvZi;
    @BindView(R.id.tv_bs)
    TextView tvBs;
    @BindView(R.id.tv_bh)
    TextView tvBh;
    @BindView(R.id.tv_qy)
    TextView tvQy;
    @BindView(R.id.tv_wb)
    TextView tvWb;
    @BindView(R.id.tv_dy)
    TextView tvDy;
    @BindView(R.id.tv_jian_jie)
    TextView tvJianJie;
    @BindView(R.id.tv_xiang_jie)
    TextView tvXiangJie;
    @BindView(R.id.text_foot)
    TextView text_foot;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.scroll_down_layout)
    ScrollLayout mScrollLayout;
    @BindView(R.id.tv_top_zi)
    TextView tvTopZi;
    @BindView(R.id.ll_jian_jie)
    LinearLayout llJianJie;
    @BindView(R.id.sv_jian_jie)
    ScrollView svJianJie;
    @BindView(R.id.tv_top_bs)
    TextView tvTopBs;
    @BindView(R.id.tv_top_bh)
    TextView tvTopBh;
    @BindView(R.id.tv_top_wb)
    TextView tvTopWb;
    @BindView(R.id.tv_top_py)
    TextView tvTopPy;
    @BindView(R.id.tv_top_dy)
    TextView tvTopDy;
    private Intent intent;
    private String word;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_han_zi_details;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        intent = getIntent();
        word = intent.getStringExtra("word");

        isLoading = true;
        toolbar.setTitle("汉字详情");
        toolbar.setNavigationIcon(R.drawable.action_bar_return);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light_bule));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        /**设置 setting*/
        mScrollLayout.setMinOffset(0);//关闭状态时最上方预留高度
        mScrollLayout.setMaxOffset((int) (ScreenUtil.getScreenHeight(this) * 0.6));//打开状态时内容显示区域的高度
        mScrollLayout.setExitOffset(ScreenUtil.dip2px(this, 100));//最低部退出状态时可看到的高度，0为不可见
        mScrollLayout.setIsSupportExit(true);//是否支持下滑退出，支持会有下滑到最底部时的回调
        mScrollLayout.setAllowHorizontalScroll(true);//是否支持横向滚动
        mScrollLayout.setToExit(); //默认位置状态，关闭、打开、底部
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        toolbar.getBackground().setAlpha(0);

    }

    @OnClick({R.id.root, R.id.tv_xiang_jie, R.id.text_foot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.root:
                mScrollLayout.scrollToExit();
                break;
            case R.id.tv_xiang_jie:
                mScrollLayout.scrollToExit();
                break;
            case R.id.text_foot:
                mScrollLayout.scrollToOpen();
                break;
        }
    }

    @Override
    protected void initData() {
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.QUERY)
                .addQuery("word", word)
                .build();
        RetrofitUtil.request(params, HanZiQuery.class, new RetrofitUtil.HttpCallBackImpl<HanZiQuery>() {
            @Override
            public void onCompleted(HanZiQuery pinyinBean) {
                if(pinyinBean.getResult()!=null){
                    setData(pinyinBean.getResult());
                }else{
                    Dialog_Exit.showDialogOneBtn(HanZiDetailsActivity.this, pinyinBean.getReason(), new Dialog_Exit.DialogConfirm() {
                        @Override
                        public void confirm() {
                            finish();
                        }
                    });
                }
            }
        });
    }
    private String getTextStr(String str){
        if(StringUtil.isNotBlank(str)){
            return str;
        }
        return "------";
    }
    private void setData(HanZiQuery.ResultBean result) {
        tvHanZi.setText(result.getZi());
        tvZi.setText(Html.fromHtml(String.format(getString(R.string.xhzd_hz), getTextStr(result.getZi()))));
        tvQy.setText(Html.fromHtml(String.format(getString(R.string.xhzd_qy), getTextStr(result.getPy()))));
        tvBh.setText(Html.fromHtml(String.format(getString(R.string.xhzd_bh), getTextStr(result.getBihua()))));
        tvBs.setText(Html.fromHtml(String.format(getString(R.string.xhzd_bs), getTextStr(result.getBushou()))));
        tvDy.setText(Html.fromHtml(String.format(getString(R.string.xhzd_dy), getTextStr(result.getPinyin()))));
        tvWb.setText(Html.fromHtml(String.format(getString(R.string.xhzd_wb), getTextStr(result.getWubi().toUpperCase()))));

        tvTopZi.setText(result.getZi());
        tvTopPy.setText(Html.fromHtml(String.format(getString(R.string.xhzd_qy), result.getPy())));
        tvTopBh.setText(Html.fromHtml(String.format(getString(R.string.xhzd_bh), result.getBihua())));
        tvTopBs.setText(Html.fromHtml(String.format(getString(R.string.xhzd_bs), result.getBushou())));
        tvTopDy.setText(Html.fromHtml(String.format(getString(R.string.xhzd_dy), result.getPinyin())));
        tvTopWb.setText(Html.fromHtml(String.format(getString(R.string.xhzd_wb), result.getWubi().toUpperCase())));

        List<String> jiaJie = result.getJijie();
        String jjStr = "";
        for (int i = 0; i < jiaJie.size(); i++) {
            jjStr += jiaJie.get(i) + "\n";
        }
        tvJianJie.setText(jjStr);

        String xqStr = "";
        List<String> xq = result.getXiangjie();
        if(xq.size()>1){
            for (int i = 0; i < xq.size(); i++) {
                xqStr += xq.get(i) + "\n";
            }
            tvXiangJie.setText(xqStr);
        }else{
            mScrollLayout.setVisibility(View.GONE);
        }
        if (loadingDoalog.isShowing()) {
            loadingDoalog.dismiss();
        }
    }

    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress >= 0) {
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
                int alpha = 255 - (int) precent;
                toolbar.getBackground().setAlpha(alpha);
                if (currentProgress == 0) {
                    llJianJie.setVisibility(View.VISIBLE);
                    svJianJie.setVisibility(View.GONE);
                } else if (currentProgress == 1) {
                    llJianJie.setVisibility(View.GONE);
                    svJianJie.setVisibility(View.VISIBLE);
                }
            }
            text_foot.setText(word + "字详情");
//            if (text_foot.getVisibility() == View.VISIBLE) ;
//                text_foot.setVisibility(View.GONE);
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
//                text_foot.setVisibility(View.VISIBLE);
                svJianJie.setVisibility(View.VISIBLE);
                llJianJie.setVisibility(View.GONE);
                text_foot.setText(getString(R.string.xhzd_shxx));
            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    };
}
