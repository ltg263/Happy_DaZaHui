package com.ltg263.happy_dzh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.adapter.SearchListAdapter;
import com.ltg263.happy_dzh.apiService.RetrofitUtil;
import com.ltg263.happy_dzh.apiService.RxHttpParams;
import com.ltg263.happy_dzh.baseManager.Constants;
import com.ltg263.happy_dzh.bean.SearchBean;
import com.ltg263.happy_dzh.fragment.MainFirstFragment;
import com.ltg263.happy_dzh.utils.IntentUtil;
import com.ltg263.happy_dzh.utils.LogUtil;
import com.ltg263.happy_dzh.view.Dialog_Exit;
import com.ltg263.happy_dzh.view.ItemSearchHead;
import com.ltg263.happy_dzh.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间：  2017/3/4 23:30
 * 邮箱；ltg263@126.com
 * 描述：根据拼英或者部首查询列表
 */
public class SearchListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {
    @BindView(R.id.ish_head_search)
    ItemSearchHead ishHeadSearch;
    @BindView(R.id.lv_search_list)
    ListView lvSearchList;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int page = 1;
    private int pagesize = 10;
    private List<SearchBean.ResultBean.ListBean> listBeens = new ArrayList<>();
    private int totalpage = 0;
    private SearchListAdapter adapter;
    private Intent intent;
    private int type;
    private String word;
    private String searchWord;
    //是不是通过搜索
    private boolean isSearch = false;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        intent = getIntent();
        type = Integer.valueOf(intent.getStringExtra("type"));
        word = intent.getStringExtra("word");
        isSearch = intent.getStringExtra("isSearch") == null ? false : true;
        if(isSearch){
            searchWord=word;
        }
        toolbar.setTitle(word);
        toolbar.setNavigationIcon(R.drawable.action_bar_return);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_title_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        isLoading = true;
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadListener(this);
        lvSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key[] = {"word"};
                String values[] = {listBeens.get(position).getZi()};
                IntentUtil.startActivity(HanZiDetailsActivity.class, key, values);
            }
        });
    }

    public void anewSearch(String searchWord, boolean isSearch) {
        this.searchWord = searchWord;
        this.isSearch = isSearch;
        if (loadingDoalog != null) {
            loadingDoalog.show();
        }
        initData();
    }

    @Override
    protected void initData() {
        LogUtil.w("***************"+type);
        String url;
        if (type == MainFirstFragment.FRAGMENT_PIN_YIN) {
            url = Constants.QUERY_PY;
            ishHeadSearch.setSearchHint("按拼音搜索");
        } else {
            url = Constants.QUERY_BS;
            ishHeadSearch.setSearchHint("按部首搜索");
        }
        ishHeadSearch.setSearchText(!isSearch ? word : searchWord);
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("word", !isSearch ? word : searchWord)
                .addQuery("page", page)
                .addQuery("pagesize", pagesize)
//                .addQuery("isjijie", 0)
//                .addQuery("isxiangjie", 0)
                .build();
        RetrofitUtil.request(params, SearchBean.class, new RetrofitUtil.HttpCallBackImpl<SearchBean>() {
            @Override
            public void onCompleted(SearchBean searchBean) {
                SearchBean.ResultBean list = searchBean.getResult();
                if (list != null) {
                    if (isSearch) {
                        word = searchWord;
                        toolbar.setTitle(word);
                    }
                    setListContent(list.getList());
                    totalpage = searchBean.getResult().getTotalpage();
                } else {
                    if (isSearch) {
                        ishHeadSearch.setSearchText(word);
                        isSearch = false;
//                        if(type == MainFirstFragment.FRAGMENT_PIN_YIN){
                        Dialog_Exit.showDialogNoListen(SearchListActivity.this, searchBean.getReason());
//                        }
                    }
                }
                if (loadingDoalog.isShowing()) {
                    loadingDoalog.dismiss();
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.setLoading(false);
                }
            }
        });
    }

    public void setListContent(List<SearchBean.ResultBean.ListBean> listContent) {
        if (page == 1) {
            listBeens.clear();
        }
        listBeens.addAll(listContent);
        if (adapter == null) {
            adapter = new SearchListAdapter(this, listBeens);
            lvSearchList.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData();
    }

    @Override
    public void onLoad() {
        if (totalpage != page) {
            page += 1;
            refreshLayout.setNotData(false);
            initData();
            return;
        }
        refreshLayout.setNotData(true);
    }
}