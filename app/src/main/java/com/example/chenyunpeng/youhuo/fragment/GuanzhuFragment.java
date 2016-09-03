package com.example.chenyunpeng.youhuo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.GuanzhuAdapter;
import com.example.chenyunpeng.youhuo.bena.GuanzhuBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenyunpeng on 2016/8/29.
 */
public class GuanzhuFragment extends BaseFragment {
    protected CircleImageView headImageGuanzhu;
    protected TextView tv1;
    protected TextView tv2;
    protected RelativeLayout head;
    protected ListView lvGuanzhu;

    @Override
    protected void initData() {
        showLoadDialog();
        new HttpUtils().post(HttpModel.FLLOW,null).DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                GuanzhuBean guanzhuBean = new Gson().fromJson(data, GuanzhuBean.class);
                List<GuanzhuBean.FollowBean> follow = guanzhuBean.getFollow();
                GuanzhuAdapter adapter=new GuanzhuAdapter(follow,a);
                lvGuanzhu.setAdapter(adapter);
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
        View inflate = inflater.inflate(R.layout.fragment_guanzhu, container, false);
             init(inflate);
        return inflate;

    }

    private void init(View rootView) {
        headImageGuanzhu = (CircleImageView) rootView.findViewById(R.id.head_image_guanzhu);
        tv1 = (TextView) rootView.findViewById(R.id.tv1);
        tv2 = (TextView) rootView.findViewById(R.id.tv2);
        head = (RelativeLayout) rootView.findViewById(R.id.head);
        lvGuanzhu = (ListView) rootView.findViewById(R.id.lv_guanzhu);
    }
}
