package com.example.chenyunpeng.youhuo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.diaglog.LoadDataDiaglog;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment {
    public Activity a;
    private View rootView;

   LoadDataDiaglog loadDataDiaglog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.a=activity;


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView = initView(inflater,container);
            initData();
            initAdapter();
        }
        return rootView;
    }
    public void initAdapter() {

    }

    protected abstract void initData();

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);
    public void showLoadDialog(){
        loadDataDiaglog = new LoadDataDiaglog(a);
        loadDataDiaglog.show();
    }
    public void dismissionLoadDialog(){
        if(loadDataDiaglog!=null)
            loadDataDiaglog.dismiss();
    }
    public  void toast(String str){
        Toast.makeText(a,str+"",Toast.LENGTH_SHORT).show();
    }
}
