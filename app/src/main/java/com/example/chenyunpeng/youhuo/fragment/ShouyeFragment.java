package com.example.chenyunpeng.youhuo.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.HomeGridviewAdapter;
import com.example.chenyunpeng.youhuo.bena.GridBean;
import com.example.chenyunpeng.youhuo.bena.HomeBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.example.chenyunpeng.youhuo.view.MyBanner;
import com.example.chenyunpeng.youhuo.view.MyGridView;
import com.example.chenyunpeng.youhuo.view.MyPullToReflash;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class ShouyeFragment extends BaseFragment implements MyPullToReflash.PullToRelashListener {
    protected ImageButton toolbarMenu;
    protected ImageButton toolbarSearch;
    protected ImageButton toolbarProductBacklook;
    private MyGridView gv;
    private MyBanner banner;
    private MyPullToReflash lv;
    private List<String> stringList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void initData() {
        new HttpUtils().post(HttpModel.RECOMMEND, "parames={\"page\":\"1\"}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                Log.e("tag",""+data.toString());
            }

            @Override
            public void failrue(String e) {
                Log.e("tag22", "" + e);
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
        toolbarMenu = (ImageButton) rootView.findViewById(R.id.toolbar_menu);
        toolbarSearch = (ImageButton) rootView.findViewById(R.id.toolbar_search);
        toolbarProductBacklook = (ImageButton) rootView.findViewById(R.id.toolbar_product_backlook);
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
                Toast.makeText(a, "jiazaishuju", Toast.LENGTH_SHORT).show();
                lv.setPullSuccess();
            }
        }, 2000);
    }

    @Override
    public void load() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(a, "jiazaishuju", Toast.LENGTH_SHORT).show();
                lv.setLoadSuccess();
            }
        }, 2000);
    }
}
