package com.example.chenyunpeng.youhuo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.PinLeiBaseFragmentAdapter;
import com.example.chenyunpeng.youhuo.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class PinLeiFragment extends BaseFragment {

    @Bind(R.id.boy_pinlei)
    RadioButton boyPinlei;
    @Bind(R.id.girl_pinlei)
    RadioButton girlPinlei;
    @Bind(R.id.lifestyle_pinlei)
    RadioButton lifestylePinlei;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.pager_pinlei)
    MyViewPager pagerPinlei;
    private List<Fragment> fragmentList;

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new PinLeiBoyfragment());
        fragmentList.add(new PinLeiGirlfragment());
        fragmentList.add(new PinLeiLifeStylefragment());
        PinLeiBaseFragmentAdapter fragmentAdapter = new PinLeiBaseFragmentAdapter(getFragmentManager(), fragmentList);
        pagerPinlei.setAdapter(fragmentAdapter);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_pinlei, container, false);
        ButterKnife.bind(this, inflate);
        boyPinlei.performClick();
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.boy_pinlei, R.id.girl_pinlei, R.id.lifestyle_pinlei})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boy_pinlei:
                pagerPinlei.setCurrentItem(0, false);

                break;
            case R.id.girl_pinlei:
                pagerPinlei.setCurrentItem(1, false);

                break;
            case R.id.lifestyle_pinlei:
                pagerPinlei.setCurrentItem(2, false);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
