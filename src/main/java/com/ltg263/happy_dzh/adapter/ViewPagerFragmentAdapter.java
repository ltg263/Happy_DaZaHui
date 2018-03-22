package com.ltg263.happy_dzh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    List<String> mAppTitles;
    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mAppTitles) {
        super(fm);
        this.fragments = fragments;
        this.mAppTitles = mAppTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mAppTitles.get(position);
    }

}
