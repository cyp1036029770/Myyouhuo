package com.example.chenyunpeng.youhuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.MyApplication;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.GouWuCheAdapter;
import com.example.chenyunpeng.youhuo.bena.GouWuCheBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.bena.itemCartBean;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GouWuCheActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {


    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.cb)
    CheckBox cb;
    @Bind(R.id.jiesuan)
    Button jiesuan;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.addToShouCang)
    Button addToShouCang;
    @Bind(R.id.bottom)
    RelativeLayout bottom;
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.bianji)
    TextView bianji;
    private List<itemCartBean> beanList = new ArrayList();
    private List<itemCartBean> shoucangList = new ArrayList();
    private GouWuCheAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
        ButterKnife.bind(this);
        tvPrice.setText("总价格为:"+0+"元");
        initData();
        cb.setOnCheckedChangeListener(this);
    }

    private void initData() {
        showLoadDialog();
        if(MyApplication.user==null){
            loadDataFromLocation();
        }else {
            loadDataFromNet();
        }

    }

    private void loadDataFromLocation() {
        //从本地读取数据
        dismissionLoadDialog();
    }

    private void loadDataFromNet() {
        new HttpUtils().post(HttpModel.CART, "parames={\"userId\":" + MyApplication.user.id + "}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                GouWuCheBean gouWuCheBean = new Gson().fromJson(data, GouWuCheBean.class);
                List<GouWuCheBean.Cart> cart = gouWuCheBean.getCart();
                for (int i = 0; i < cart.size(); i++) {
                    GouWuCheBean.Cart caetbean = cart.get(i);
                    itemCartBean itemCartBean = new itemCartBean();
                    itemCartBean.setCheck(false);
                    itemCartBean.setImgpath(caetbean.getImgpath());
                    itemCartBean.setColor(caetbean.getColor());
                    itemCartBean.setNum(caetbean.getNum());
                    itemCartBean.setPrice(caetbean.getPrice());
                    itemCartBean.setSize(caetbean.getSize());
                    itemCartBean.setTitle(caetbean.getTitle());
                    beanList.add(itemCartBean);
                }

                adapter = new GouWuCheAdapter(beanList, GouWuCheActivity.this);
                lv.setAdapter(adapter);
                dismissionLoadDialog();
            }

            @Override
            public void failrue(String e) {
                Toast.makeText(GouWuCheActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                dismissionLoadDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.gouwuche_out);
    }

    public void back(){
        onBackPressed();
        finish();
    }

    @OnClick({R.id.back, R.id.bianji, R.id.jiesuan, R.id.addToShouCang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                back();
                break;
            case R.id.bianji:
                qiehuan();
                break;
            case R.id.jiesuan:
                //计算被选中的货物的总价格
                if(MyApplication.user==null){
                      Login();
                }else  {
                    jiesuan();
                }
                break;
            case R.id.addToShouCang:
                //吧选中的货物加入收藏夹中,并删除选中的物品
                addToShouCang();
                break;
        }
    }

    private void addToShouCang() {
       for(int i=0;i<beanList.size();i++){
           itemCartBean bean = beanList.get(i);
           boolean check = bean.isCheck();
           if(check){
               //夹如两一个集合中,并删除在该集合中的数据
                   shoucangList.add(bean);
           }
       }
        beanList.removeAll(shoucangList);
        adapter.notifyDataSetChanged();
    }

    private void Login() {
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.fade_in_boys,R.anim.fade_out_choose);
    }

    private void jiesuan() {
        if(beanList.size()<=0){
            return;
        }
        int countNum=0;
        //计算被选中的商品的价格和,并跳转Activity
        for(itemCartBean bean:beanList){
            if(bean.isCheck()){
                float v = Float.parseFloat(bean.getPrice());
                countNum+=v;
            }
        }
        tvPrice.setText("总价格为:"+countNum+"元");
        //清理购物车中被选中支付的货物
        //跳转到支付的Activity中
        clear();
        start();
    }

    private void clear() {

    }

    private void start() {
        Toast.makeText(this,"支付了",Toast.LENGTH_SHORT).show();
    }


    private void qiehuan() {
        if(bianji.getText().toString().equals("编辑")){
            adapter.setType(1);
            bianji.setText("完成");
            addToShouCang.setVisibility(View.VISIBLE);
        }else {
            adapter.setType(0);
            bianji.setText("编辑");
            addToShouCang.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        selectOrnnselect();
        //? 可以选择实现,如果但checkbox全部被选中后, cb被选中



    }
    private void selectOrnnselect() {
        if(beanList.size()<=0)return;
        boolean checked = cb.isChecked();
        if(checked){
            //如果选中的状态是true,吧所有的checkbox置为true
            for(itemCartBean bean:beanList){
                bean.setCheck(true);
            }
        }else {
            //如果checkbox没有被选中,则吧所有的选中状态置为false
            for(itemCartBean bean:beanList){
                bean.setCheck(false);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
