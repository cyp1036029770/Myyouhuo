package com.example.chenyunpeng.youhuo.fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class MineFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        TextView tv=new TextView(a);
        tv.setText(getClass().getSimpleName().toString());
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
