package com.example.chenyunpeng.youhuo.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BoyBean;
import com.example.chenyunpeng.youhuo.bena.FenLeiBoyBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class PinLeiBoyfragment extends PinLeiBaseFragment {


    private List<BoyBean> list;
    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add(new BoyBean("上衣", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("裤装", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("鞋靴", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("包类", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("服配", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("内衣/家居服", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("美妆/个护", R.mipmap.homepage_limited_disabled,true));
       boyBeanList.addAll(list);

    }

    @Override
    public void initAdapter() {
        super.initAdapter();
    }

    @Override
    public void RequestData() {
        super.RequestData();
        new HttpUtils().post(HttpModel.FENLEIBOY,null).DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                FenLeiBoyBean leiBoyBean = new Gson().fromJson(data, FenLeiBoyBean.class);
                List<FenLeiBoyBean.BoyBean> boy = leiBoyBean.getBoy();
                List<String> list = new ArrayList<String>();
                   for(int i=0;i<boy.size();i++){
                       list.add(boy.get(i).getName());
                   }
                ArrayAdapter<String>  ad=new ArrayAdapter<String>(a,android.R.layout.simple_list_item_1,list);
                pinleiBaseRight.setAdapter(ad);
            }

            @Override
            public void failrue(String e) {
                Toast.makeText(a,"数据加载出现错误,错误信息是"+e,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
