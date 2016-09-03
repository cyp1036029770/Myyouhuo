package com.example.chenyunpeng.youhuo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class FeileiPagerAdapter extends FragmentPagerAdapter {
    List<String> titleList;
    List<Fragment> fragmentList;
    public FeileiPagerAdapter(FragmentManager fm,    List<String> titleList,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
