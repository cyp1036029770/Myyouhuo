package com.example.chenyunpeng.youhuo.utils;


import com.example.chenyunpeng.youhuo.MyApplication;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class SPutils {
    public  static   void save(String key,String values){
        MyApplication.app.getSharedPreferences("config",0).edit().putString(key,values).commit();
    }
    public  static String get(String key){
        return  MyApplication.app.getSharedPreferences("config",0).getString(key,"");
    }
    public static void clear(String key){
        MyApplication.app.getSharedPreferences("config",0).edit().remove(key).commit();
    }
}
