package com.example.chenyunpeng.youhuo.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.activity.BrandActivity;
import com.example.chenyunpeng.youhuo.adapter.SortGridAdapter;
import com.example.chenyunpeng.youhuo.bena.FllowGridBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.example.chenyunpeng.youhuo.view.PullToReflashGridView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/9/4.
 */
public class SortBaseFragment extends BaseFragment implements PullToReflashGridView.PullSuccessListener {

    private PullToReflashGridView gridsort;
    public List<FllowGridBean.FollowBean.GoodsBean> goodsBeanList = new ArrayList<>();
    public SortGridAdapter adapter;

    @Override
    protected void initData() {
        showLoadDialog();
        loadData();
    }

    private void loadData() {
        new HttpUtils().post(HttpModel.FLLOW, "params={\"categoryId:\"" + 1 + "}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                FllowGridBean fllowBean = new Gson().fromJson(data, FllowGridBean.class);
                List<FllowGridBean.FollowBean> follow = fllowBean.getFollow();
                for (int i = 0; i < follow.size(); i++) {
                    FllowGridBean.FollowBean followBean = follow.get(i);
                    List<FllowGridBean.FollowBean.GoodsBean> goods = followBean.getGoods();
                    for (int j = 0; j < goods.size(); j++) {
                        goodsBeanList.add(goods.get(j));
                    }
                }
/*
                SortGridAdapter adapter=new SortGridAdapter(goodsBeanList,a);
                gridsort.setAdapter(adapter);
                dismissionLoadDialog();*/
                paixu();
                i--;
                adapter.notifyDataSetChanged();
                dismissionLoadDialog();

            }

            @Override
            public void failrue(String e) {
                toast(e.toString());
                dismissionLoadDialog();
            }
        });
    }

    int i = 0;

    private void paixu() {
        if (i % 2 == 0) {
            jiangxu();
        } else {
            shengxu();
        }
        i++;
    }

    public void shengxu() {

    }

    public void jiangxu() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.framgment_sort, container, false);
        this.gridsort = (PullToReflashGridView) inflate.findViewById(R.id.grid_sort);
        adapter = new SortGridAdapter(goodsBeanList, a);
        gridsort.setAdapter(adapter);
        gridsort.setOnPullSuccessListener(this);
        gridsort.setOnItemClickListenener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FllowGridBean.FollowBean.GoodsBean goodsBean = goodsBeanList.get(position);
                Intent intent=new Intent(a, BrandActivity.class);
                intent.putExtra("goods",goodsBean);
                startActivity(intent);
                a.overridePendingTransition(R.anim.translate_xiangqing_in,R.anim.translate_pinpai_out);
            }
        });
        return inflate;
    }

    @Override
    public void pull() {
        loadData();
        gridsort.pullSuccess();
    }

    @Override
    public void load() {
        loadData();
        gridsort.loadSuccess();
    }
}
