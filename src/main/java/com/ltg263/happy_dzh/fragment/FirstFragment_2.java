package com.ltg263.happy_dzh.fragment;

import android.view.View;
import android.widget.ExpandableListView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.adapter.PinnedHeaderExpandableAdapter;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.PinyinOrBiHuaBean;
import com.ltg263.happy_dzh.fragment.base.BaseFragment;
import com.ltg263.happy_dzh.view.PinnedHeaderExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：按拼音查询汉字
 */

public class FirstFragment_2 extends BaseFragment {
    private PinnedHeaderExpandableListView explistview;
    private Map<String, List<PinyinOrBiHuaBean.ResultBean>> groupData = new HashMap<>();
    private List<String> keyData= new ArrayList<>();
    private int expandFlag = -1;//控制列表的展开
    private PinnedHeaderExpandableAdapter adapter;

    @Override
    public int setOnCreateView() {
        return R.layout.fragment_first_2;
    }

    @Override
    protected void initUI() {
        explistview = (PinnedHeaderExpandableListView)mView.findViewById(R.id.explistview);
    }
    public void setadb(){

    }
    /**
     * 在Fragment显示出来调用的方法
     * @param isVisibleToUser 当前Fragment是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            if(adapter==null){
                loadView(true);
                return;
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void loadData() {
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PINYIN)
                .build();
        RetrofitUtil.request(params, PinyinOrBiHuaBean.class, new RetrofitUtil.HttpCallBackImpl<PinyinOrBiHuaBean>() {
            @Override
            public void onCompleted(PinyinOrBiHuaBean pinyinBean) {
                List<PinyinOrBiHuaBean.ResultBean> pinyinBeans = pinyinBean.getResult();
                setMap(pinyinBeans);
            }
        });

        //设置单个分组展开
//        explistview.setOnGroupClickListener(new GroupClickListener());
    }

    /**
     * 将List数据转化为Map
     * @param pinyinBeans
     */
    private void setMap(List<PinyinOrBiHuaBean.ResultBean> pinyinBeans) {
        for(PinyinOrBiHuaBean.ResultBean pinyin:pinyinBeans){
            if(!keyData.contains(pinyin.getPinyin_key())){
                keyData.add(pinyin.getPinyin_key());
            }
        }
        for(String key:keyData){
            List<PinyinOrBiHuaBean.ResultBean> childrenData = new ArrayList<>();
            for(PinyinOrBiHuaBean.ResultBean pinyin:pinyinBeans){
                if(key.equals(pinyin.getPinyin_key())){
                    childrenData.add(pinyin);
                }
            }
            if(childrenData.size()>0){
                groupData.put(key,childrenData);
            }
        }
        //设置悬浮头部VIEW
        explistview.setHeaderView(mContext.getLayoutInflater().inflate(R.layout.elv_item_group, explistview, false));
        adapter = new PinnedHeaderExpandableAdapter(keyData,groupData, mContext,explistview,MainFirstFragment.FRAGMENT_PIN_YIN);
        explistview.setAdapter(adapter);
        loadingDialog.dismiss();
    }

    class GroupClickListener implements ExpandableListView.OnGroupClickListener {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            if (expandFlag == -1) {
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            } else if (expandFlag == groupPosition) {
                explistview.collapseGroup(expandFlag);
                expandFlag = -1;
            } else {
                explistview.collapseGroup(expandFlag);
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            }
            return true;
        }
    }
}
