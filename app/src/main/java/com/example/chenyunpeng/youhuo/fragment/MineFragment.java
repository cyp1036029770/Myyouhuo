package com.example.chenyunpeng.youhuo.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.activity.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    protected CircleImageView headImage;
    protected TextView titleHead;
    protected RelativeLayout headV1;
    protected ImageView iv;
    protected TextView tv1;
    protected TextView tv2;
    protected ViewFlipper vfHead;
    private ListView lv;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        this.lv = (ListView) inflate.findViewById(R.id.lv);
        View head = View.inflate(a, R.layout.head_item, null);
        initHead(head);
        lv.addHeaderView(head);
        return inflate;

    }

    private void initHead(View rootView) {
        headImage = (CircleImageView) rootView.findViewById(R.id.head_image);
        titleHead = (TextView) rootView.findViewById(R.id.title_head);
        headV1 = (RelativeLayout) rootView.findViewById(R.id.head_v1);
        iv = (ImageView) rootView.findViewById(R.id.iv);
        tv1 = (TextView) rootView.findViewById(R.id.tv1);
        tv2 = (TextView) rootView.findViewById(R.id.tv2);
        vfHead = (ViewFlipper) rootView.findViewById(R.id.vf_head);
        vfHead.setFlipInterval(6000);
        vfHead.setAutoStart(true);
        vfHead.setInAnimation(a,R.anim.enter_login);
        vfHead.setOutAnimation(a,R.anim.mine_out);
        headImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(a, LoginActivity.class));
      a.overridePendingTransition(R.anim.enter_login,R.anim.mine_out);
    }
}
