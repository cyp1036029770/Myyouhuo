package com.example.chenyunpeng.youhuo.fragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BoyBean;
import com.example.chenyunpeng.youhuo.bena.FenLeiLifeStyleBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class PinLeiLifeStylefragment extends PinLeiBaseFragment {


    private List<BoyBean> list;
    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add(new BoyBean("数码3C", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("耳机", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("相机", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("手表", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("数码配件", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("家居生活", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("美妆/个护", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("雨伞/雨衣", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("杯子/水壶", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("自行车", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("玩具娱乐", R.mipmap.homepage_limited_disabled,true));
        list.add(new BoyBean("文具", R.mipmap.homepage_limited_disabled,true));
        boyBeanList.addAll(list);
    }
    @Override
    public void initAdapter() {
        super.initAdapter();
    }

    @Override
    public void RequestData() {
        super.RequestData();
        new HttpUtils().post(HttpModel.FENLEILIFESTYLE,null).DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                FenLeiLifeStyleBean leiBoyBean = new Gson().fromJson(data, FenLeiLifeStyleBean.class);
                List<FenLeiLifeStyleBean.LifeBean> boy = leiBoyBean.getLife();
                List<String> list = new ArrayList<String>();
                for(int i=0;i<boy.size();i++){
                    list.add(boy.get(i).getName());
                }
                ArrayAdapter<String> ad=new ArrayAdapter<String>(a,android.R.layout.simple_list_item_1,list);
                pinleiBaseRight.setAdapter(ad);
            }

            @Override
            public void failrue(String e) {
                Toast.makeText(a,"数据加载出现错误,错误信息是"+e,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
