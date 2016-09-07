package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.chenyunpeng.share.ShareDiaglog;
import com.example.chenyunpeng.youhuo.diaglog.LoadDataDiaglog;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class BaseActivity extends FragmentActivity {
    public int screenwidth;
    public int screenheight;
    private ShareDiaglog shareDiaglog;
    private LoadDataDiaglog loadDataDiaglog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenwidth = this.getWindowManager().getDefaultDisplay().getWidth();
        screenheight = this.getWindowManager().getDefaultDisplay().getHeight();
    }

    public void showLoadDialog(){
        loadDataDiaglog = new LoadDataDiaglog(this);
    }
    public void dismissionLoadDialog(){
        if(loadDataDiaglog!=null)
            loadDataDiaglog.dismiss();
    }
    public  void toast(String str){
        Toast.makeText(this,""+str,Toast.LENGTH_SHORT).show();
    }
    public  void shouShareDiaglog(){
        shareDiaglog = new ShareDiaglog(this);
        shareDiaglog.show();
    }
    public  void dismissShareDiaglog() {
        if (shareDiaglog != null) {
            shareDiaglog.dismiss();
        }
    }
}
