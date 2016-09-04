package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.MyApplication;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.GouWuCheAdapter;
import com.example.chenyunpeng.youhuo.bena.GouWuCheBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.bena.itemCartBean;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GouWuCheActivity extends BaseActivity {

    private android.widget.RelativeLayout top;
    private android.widget.CheckBox cb;
    private android.widget.Button jiesuan;
    private android.widget.TextView tvprice;
    private android.widget.Button addToShouCang;
    private android.widget.RelativeLayout bottom;
    private android.widget.ListView lv;
    private List<itemCartBean> beanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
       initView();
        initData();
    }

    private void initData() {
        new HttpUtils().post(HttpModel.CART,"parames={\"userId\":"+ MyApplication.user.id+"}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                GouWuCheBean gouWuCheBean = new Gson().fromJson(data, GouWuCheBean.class);
                List<GouWuCheBean.Cart> cart = gouWuCheBean.getCart();
                for(int i=0;i<cart.size();i++){
                    GouWuCheBean.Cart caetbean = cart.get(i);
                    itemCartBean itemCartBean=new itemCartBean();
                    itemCartBean.setCheck(false);
                    itemCartBean.setImgpath(caetbean.getImgpath());
                    itemCartBean.setColor(caetbean.getColor());
                    itemCartBean.setNum(caetbean.getNum());
                    itemCartBean.setPrice(caetbean.getPrice());
                    itemCartBean.setSize(caetbean.getSize());
                    itemCartBean.setTitle(caetbean.getTitle());
                    beanList.add(itemCartBean);
                }

                GouWuCheAdapter adapter=new GouWuCheAdapter(beanList,GouWuCheActivity.this);
                lv.setAdapter(adapter);
            }

            @Override
            public void failrue(String e) {

            }
        });
    }

    private void initView() {
        this.lv = (ListView) findViewById(R.id.lv);
        this.bottom = (RelativeLayout) findViewById(R.id.bottom);
        this.addToShouCang = (Button) findViewById(R.id.addToShouCang);
        this.tvprice = (TextView) findViewById(R.id.tv_price);
        this.jiesuan = (Button) findViewById(R.id.jiesuan);
        this.cb = (CheckBox) findViewById(R.id.cb);
        this.top = (RelativeLayout) findViewById(R.id.top);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       overridePendingTransition(0,R.anim.gouwuche_out);
    }
}
