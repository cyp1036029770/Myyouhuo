package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.chenyunpeng.youhuo.diaglog.LoadDataDiaglog;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class BaseActivity extends FragmentActivity {
    public int screenwidth;
    public int screenheight;
    public LoadDataDiaglog dataDiaglog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenwidth = this.getWindowManager().getDefaultDisplay().getWidth();
        screenheight = this.getWindowManager().getDefaultDisplay().getHeight();
    }

    public void showLoadDialog(){
        dataDiaglog = new LoadDataDiaglog(this);
        dataDiaglog.show();
    }
    public void dismissionLoadDialog(){
        if(dataDiaglog!=null)
            dataDiaglog.dismiss();
    }
}
