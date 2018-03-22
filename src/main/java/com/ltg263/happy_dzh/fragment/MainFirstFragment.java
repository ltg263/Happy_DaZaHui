package com.ltg263.happy_dzh.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.adapter.ViewPagerFragmentAdapter;
import com.ltg263.happy_dzh.fragment.base.BaseFragment;
import com.ltg263.happy_dzh.view.ItemSearchHead;
import com.ltg263.happy_dzh.view.SlidingViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：App首页
 */

public class MainFirstFragment extends BaseFragment {

    public static final int FRAGMENT_HAN_ZI = -1;
    public static final int FRAGMENT_PIN_YIN = 0;
    public static final int FRAGMENT_BU_SHOU = 1;
    @BindView(R.id.tab_main_1)
    TabLayout tabMain1;
    @BindView(R.id.vp_main_1)
    SlidingViewPager svpMain1;
    FirstFragment_1 fragment_1;
    FirstFragment_2 fragment_2;
    FirstFragment_3 fragment_3;
    @BindView(R.id.ish_head_search)
    ItemSearchHead ishHeadSearch;
    private List<Fragment> fragments = new ArrayList<>();//页卡视图集合
    private List<String> mAppTitles = new ArrayList<>();
    private ViewPagerFragmentAdapter mPagerAdapter;

    @Override
    public int setOnCreateView() {
        return R.layout.fragment_main_first;
    }

    @Override
    protected void initUI() {
        loadView(false);
        setAssociatedVp();
    }

    @Override
    protected void loadData() {
        ishHeadSearch.setType(FRAGMENT_HAN_ZI);
        ishHeadSearch.setSearchHint("搜索新闻标题");
        //标题
        mAppTitles.add("新闻");
        mAppTitles.add("拼音");
        mAppTitles.add("部首");
    }

    /**
     * 关联ViewPager
     */
    private void setAssociatedVp() {
        fragment_1 = new FirstFragment_1();
        fragment_2 = new FirstFragment_2();
        fragment_3 = new FirstFragment_3();
        fragments.add(fragment_1);
        fragments.add(fragment_2);
        fragments.add(fragment_3);
        mPagerAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), fragments, mAppTitles);
        svpMain1.setAdapter(mPagerAdapter);
        svpMain1.setNoScroll(true);
        svpMain1.setOffscreenPageLimit(fragments.size());
        tabMain1.setTabTextColors(getResources().getColor(R.color.app_head), getResources().getColor(R.color.colorAccent));
        tabMain1.setTabMode(TabLayout.MODE_FIXED);
        tabMain1.setSelectedTabIndicatorHeight(10);
        tabMain1.setupWithViewPager(svpMain1);
        OnListnerPager();
    }


    public void OnListnerPager() {
        svpMain1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ishHeadSearch.etSearchChangedListener();
                switch (position) {
                    case 0:
                        ishHeadSearch.setType(FRAGMENT_HAN_ZI);
                        ishHeadSearch.setSearchHint("搜索新闻标题");
                        break;
                    case 1:
                        ishHeadSearch.setType(FRAGMENT_PIN_YIN);
                        ishHeadSearch.setSearchHint("请输入拼音");
                        break;
                    case 2:
                        ishHeadSearch.setType(FRAGMENT_BU_SHOU);
                        ishHeadSearch.setSearchHint("请输入部首");
                        break;
                }
                ishHeadSearch.setSearchText("");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}