package com.example.chenyunpeng.youhuo;

import android.app.Application;
import android.provider.UserDictionary;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class MyApplication extends Application {
    public  static  MyApplication app;
    public static  User user=new User();

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
