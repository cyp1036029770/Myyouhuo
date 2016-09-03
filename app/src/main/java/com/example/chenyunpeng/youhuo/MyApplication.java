package com.example.chenyunpeng.youhuo;

import android.app.Application;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class MyApplication extends Application {
    public  static  MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
