package com.example.chenyunpeng.youhuo.utils;

import com.example.chenyunpeng.youhuo.MyApplication;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class Dp2Px {
    public   static  int dp2px(int values){
        return (int) (MyApplication.app.getResources().getDisplayMetrics().density*values+0.5f);
    }

}
