package com.example.chenyunpeng.youhuo.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class GuangFragment extends BaseFragment implements View.OnClickListener {
    private android.widget.ImageButton toolbarmenu;
    private TextView guang;
    private TextView shequ;
    private TextView show;
    private android.widget.ImageButton likebutton;
    private android.support.v4.view.ViewPager pager;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_guang, container, false);
         init(inflate);
        return inflate;
    }

    private void init(View inflate) {
        this.pager = (ViewPager) inflate.findViewById(R.id.pager);
        this.likebutton = (ImageButton) inflate.findViewById(R.id.likebutton);
        this.show = (TextView) inflate.findViewById(R.id.show);
        this.shequ = (TextView) inflate.findViewById(R.id.shequ);
        this.guang = (TextView) inflate.findViewById(R.id.guang);
        this.toolbarmenu = (ImageButton) inflate.findViewById(R.id.toolbar_menu);
        guang.setOnClickListener(this);
        show.setOnClickListener(this);
        shequ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guang:
                break;
            case R.id.show:
                break;
            case R.id.shequ:
                break;
        }
    }
}
