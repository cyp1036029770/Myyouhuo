package com.example.chenyunpeng.youhuo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.HomeGridviewAdapter;
import com.example.chenyunpeng.youhuo.adapter.ShouYeGridAdapter;
import com.example.chenyunpeng.youhuo.bena.GridBean;
import com.example.chenyunpeng.youhuo.bena.HomeBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.example.chenyunpeng.youhuo.view.MyBanner;
import com.example.chenyunpeng.youhuo.view.MyGridView;
import com.example.chenyunpeng.youhuo.view.MyPullToReflash;
import com.google.gson.Gson;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class ShouyeFragment extends BaseFragment implements MyPullToReflash.PullToRelashListener, View.OnClickListener {
    protected ImageButton toolbarMenu;
    protected ImageButton toolbarSearch;
    private MyGridView gv;
    private MyBanner banner;
    private MyPullToReflash lv;
    private List<String> stringList;
    private ArrayAdapter<String> adapter;
    List<List<HomeBean.BrandBean>> beanList = new ArrayList<>();
    private ImageButton productbacklook;


    @Override
    protected void initData() {
        showLoadDialog();
       loadData();
    }

    private void loadData() {
        new HttpUtils().post(HttpModel.HOMEPAGER, "parames={\\\"shop\\\":\\\"1\\\"}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                HomeBean homeBean = new Gson().fromJson(data, HomeBean.class);
                beanList.clear();;
                Log.e("tagsss",""+homeBean.getBrand().toString());
                beanList.add(homeBean.getBrand());
                beanList.add(homeBean.getMen());
                beanList.add(homeBean.getMenpants());
                beanList.add(homeBean.getAccessories());
                beanList.add(homeBean.getOther());
                ShouYeGridAdapter adapter=new ShouYeGridAdapter(beanList,a);
                lv.setAdapter(adapter);

                dismissionLoadDialog();
            }

            @Override
            public void failrue(String e) {
                toast(""+e);
                dismissionLoadDialog();
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_shouye, container, false);

        init(inflate);
        return inflate;


    }

    private void init(View rootView) {
        productbacklook = (ImageButton) rootView.findViewById(R.id.toolbar_product_backlook);
        toolbarMenu = (ImageButton) rootView.findViewById(R.id.toolbar_menu);
        toolbarSearch = (ImageButton) rootView.findViewById(R.id.toolbar_search);
        productbacklook.setOnClickListener(this);
        lv = (MyPullToReflash) rootView.findViewById(R.id.lv_home);
        lv.setOnPullToRelashListener(this);
        initHead();


    }

    private void initHead() {
        banner = new MyBanner(a);
        banner.LoadData(HttpModel.BANNER, null);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(200));
        banner.setLayoutParams(params);

        initgridviewData();
        lv.addHeadView(banner);
        lv.addHeadView(gv);


        String[] stringArray = lv.getResources().getStringArray(R.array.arr);
        stringList = Arrays.asList(stringArray);
        adapter = new ArrayAdapter<String>(a, android.R.layout.simple_list_item_1, stringList);
        lv.setAdapter(adapter);
    }

    private void initgridviewData() {
        gv = new MyGridView(a);
        gv.setNumColumns(4);
        List<GridBean> list = new ArrayList<>();
        list.add(new GridBean("新品到站", R.mipmap.btn_xpdz_n));
        list.add(new GridBean("国际优选", R.mipmap.btn_qqyx_n));
        list.add(new GridBean("明星原创", R.mipmap.btn_qxsc_n));
        list.add(new GridBean("全部原创", R.mipmap.btn_qbpl_n));
        list.add(new GridBean("潮流话题", R.mipmap.btn_mxcp_n));
        list.add(new GridBean("新年推荐", R.mipmap.btn_cptj));
        list.add(new GridBean("潮人街拍", R.mipmap.btn_dpzn_n));
        list.add(new GridBean("折扣区", R.mipmap.btn_zkjx_n));
        HomeGridviewAdapter adapter = new HomeGridviewAdapter(list, a);
        gv.setAdapter(adapter);
    }


    @Override
    public void pull() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                Toast.makeText(a, "加载数据成功", Toast.LENGTH_SHORT).show();
                lv.setPullSuccess();
            }
        }, 2000);
    }

    @Override
    public void load() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                Toast.makeText(a, "加载数据成功", Toast.LENGTH_SHORT).show();
                lv.setLoadSuccess();
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        saomiao();
    }
    public  void saomiao(){
        Log.e("tagggggg","dianjiewole");
        Intent openCameraIntent = new Intent(a, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }


}
