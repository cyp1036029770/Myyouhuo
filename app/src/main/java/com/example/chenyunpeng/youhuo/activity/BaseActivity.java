package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class BaseActivity extends FragmentActivity {
    public int screenwidth;
    public int screenheight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenwidth = this.getWindowManager().getDefaultDisplay().getWidth();
        screenheight = this.getWindowManager().getDefaultDisplay().getHeight();
    }
}
