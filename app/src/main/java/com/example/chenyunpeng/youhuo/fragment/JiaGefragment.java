package com.example.chenyunpeng.youhuo.fragment;

import com.example.chenyunpeng.youhuo.bena.FllowGridBean;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class JiaGefragment extends SortBaseFragment {
    @Override
    public void shengxu() {
        super.shengxu();

        Collections.sort(goodsBeanList, new Comparator<FllowGridBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FllowGridBean.FollowBean.GoodsBean lhs, FllowGridBean.FollowBean.GoodsBean rhs) {
                return  Float.compare( Float.parseFloat(lhs.getPrice()),Float.parseFloat(rhs.getPrice()));
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void jiangxu() {
        super.jiangxu();
        Collections.sort(goodsBeanList, new Comparator<FllowGridBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FllowGridBean.FollowBean.GoodsBean lhs, FllowGridBean.FollowBean.GoodsBean rhs) {
                return Float.compare(Float.parseFloat(rhs.getPrice()), Float.parseFloat(lhs.getPrice()));

            }
        });
        adapter.notifyDataSetChanged();
    }
}
