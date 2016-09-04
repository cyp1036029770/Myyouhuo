package com.example.chenyunpeng.youhuo.fragment;

import com.example.chenyunpeng.youhuo.bena.FllowGridBean;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class ZheKouFragment extends SortBaseFragment {
    @Override
    public void shengxu() {
        super.shengxu();
        Collections.sort(goodsBeanList, new Comparator<FllowGridBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FllowGridBean.FollowBean.GoodsBean lhs, FllowGridBean.FollowBean.GoodsBean rhs) {
                float dis1 = 1 - StringToFloat(lhs.getDistance()) / StringToFloat(lhs.getPrice());
                return Float.compare((1 - StringToFloat(lhs.getDistance()) / StringToFloat(lhs.getPrice())),(1 - StringToFloat(rhs.getDistance()) / StringToFloat(rhs.getPrice())));
            }
        });
        adapter.notifyDataSetChanged();
    }
  public  float   StringToFloat(String string){
        return Float.parseFloat(string);
    }
    @Override
    public void jiangxu() {
        super.jiangxu();
        Collections.sort(goodsBeanList, new Comparator<FllowGridBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FllowGridBean.FollowBean.GoodsBean lhs, FllowGridBean.FollowBean.GoodsBean rhs) {
                float dis1 = 1 - StringToFloat(lhs.getDistance()) / StringToFloat(lhs.getPrice());
                return Float.compare((1 - StringToFloat(rhs.getDistance()) / StringToFloat(rhs.getPrice())),(1 - StringToFloat(lhs.getDistance()) / StringToFloat(lhs.getPrice())));
            }
        });
        adapter.notifyDataSetChanged();
    }
}
