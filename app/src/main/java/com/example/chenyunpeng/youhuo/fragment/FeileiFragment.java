package com.example.chenyunpeng.youhuo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.FeileiPagerAdapter;
import com.example.chenyunpeng.youhuo.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class FeileiFragment extends BaseFragment {

    @Bind(R.id.feilei_menu)
    ImageView feileiMenu;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.topBar)
    RelativeLayout topBar;
    @Bind(R.id.viewpager_feilei)
    MyViewPager viewpagerFeilei;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void initData() {
        titleList = new ArrayList<>();
        titleList.add("品类");
        titleList.add("品牌");
        titleList.add("关注");
        fragmentList = new ArrayList<>();
        fragmentList.add(new PinLeiFragment());
        fragmentList.add(new PinpaiFragment());
        fragmentList.add(new GuanzhuFragment());
        FragmentManager manager = getFragmentManager();
        FeileiPagerAdapter adapter = new FeileiPagerAdapter(manager, titleList, fragmentList);
        viewpagerFeilei.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpagerFeilei);

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_feilei, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
