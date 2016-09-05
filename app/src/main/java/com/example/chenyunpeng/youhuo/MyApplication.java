package com.example.chenyunpeng.youhuo;

import android.app.Application;
import android.provider.UserDictionary;

import com.example.chenyunpeng.youhuo.utils.SPutils;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class MyApplication extends Application {
    public  static  MyApplication app;
    public static  User user;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        String loggin = SPutils.get("loggin");
        Long aLong = Long.getLong(loggin);
        if(aLong!=null&&user.upDateTime<System.currentTimeMillis()){
            user=new User();
        }
    }
}
