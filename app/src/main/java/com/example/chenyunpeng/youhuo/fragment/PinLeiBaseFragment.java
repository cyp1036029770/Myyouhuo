package com.example.chenyunpeng.youhuo.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.PinLeiBaseAdapter;
import com.example.chenyunpeng.youhuo.bena.BoyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class PinLeiBaseFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.pinlei_base)
    ListView pinleiBase;
    @Bind(R.id.jiantou)
    ImageView jiantou;
    @Bind(R.id.pinlei_base_right)
    ListView pinleiBaseRight;
    @Bind(R.id.Relativety_pinlei)
    LinearLayout RelativetyPinlei;
    private int width;
    private int childPosition = -1;
    public List<BoyBean> boyBeanList = new ArrayList<>();
    private int height;
    private ObjectAnimator animator;

    @Override
    protected void initData() {


    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        PinLeiBaseAdapter adapter = new PinLeiBaseAdapter(boyBeanList, a);
        pinleiBase.setAdapter(adapter);
        pinleiBase.setOnItemClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_pinlei_baseitem, container, false);
        ButterKnife.bind(this, inflate);
        iniAnimation();
        return inflate;
    }

    private void iniAnimation() {
        animator = ObjectAnimator.ofFloat(RelativetyPinlei, "translationX", 0, 0);
        animator.setDuration(300);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        width = a.getWindowManager().getDefaultDisplay().getWidth();
        height = a.getWindowManager().getDefaultDisplay().getHeight();
        ButterKnife.bind(this, rootView);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) RelativetyPinlei.getLayoutParams();
        params.width = width / 2;
        RelativetyPinlei.setLayoutParams(params);
        RelativetyPinlei.setTranslationX(width / 2);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        moveJianTou(position);
        if (position != childPosition && childPosition >= 0) {
            RequestData();
            childPosition = position;
        } else if (childPosition == -1) {
            openRightListView();
            childPosition = position;
        } else {
            closeRightListView();
            childPosition = -1;
        }
    }

    public void RequestData() {
     }

    private void moveJianTou(int position) {
        int i = position - pinleiBase.getFirstVisiblePosition();
        View childAt = pinleiBase.getChildAt(i);
        int y = childAt.getHeight() / 2 - jiantou.getHeight() / 2 + childAt.getTop();
        jiantou.setTranslationY(y);
        RequestData();
    }

    public void openRightListView() {
        animator.cancel();
        animator.setFloatValues(width, 0);
        animator.start();
       // RelativetyPinlei.setTranslationX(0);
    }

    public void closeRightListView() {
        animator.cancel();
        animator.setFloatValues(0, width);
        animator.start();
       // RelativetyPinlei.setTranslationX(width);
    }
}
