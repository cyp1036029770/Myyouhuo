package com.example.chenyunpeng.youhuo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.GuanzhuAdapter;
import com.example.chenyunpeng.youhuo.bena.GuanzhuBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;
import java.util.List;
/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class GouWuCheFragment extends BaseFragment implements View.OnClickListener {
    protected ImageView shopcart;
    protected TextView tvGouwuche;
    protected RelativeLayout topGouwuche;
    protected ListView lvGouwuche;
    private Button gouwuche_btn;

    @Override
    protected void initData() {
        showLoadDialog();
        new HttpUtils().post(HttpModel.FLLOW,null).DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                GuanzhuBean guanzhuBean = new Gson().fromJson(data, GuanzhuBean.class);
                List<GuanzhuBean.FollowBean> follow = guanzhuBean.getFollow();
                GuanzhuAdapter adapter=new GuanzhuAdapter(follow,a);
                lvGouwuche.setAdapter(adapter);
                dismissionLoadDialog();
            }
            @Override
            public void failrue(String e) {
                dismissionLoadDialog();
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_gouwuche_notlogin, container, false);
        init(inflate);
        View headview=View.inflate(a,R.layout.gouwuche_head,null);
        lvGouwuche.addHeaderView(headview);
        gouwuche_btn = (Button) headview.findViewById(R.id.gouwuche_btn);
        gouwuche_btn.setOnClickListener(this);
        return inflate;

    }

    private void init(View rootView) {
        lvGouwuche = (ListView) rootView.findViewById(R.id.lv_gouwuche);
    }

    @Override
    public void onClick(View v) {
        //跳转到另一activity
    }
}
