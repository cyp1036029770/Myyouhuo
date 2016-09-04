package com.example.chenyunpeng.youhuo.diaglog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BranBean;
import com.example.chenyunpeng.youhuo.bena.FllowGridBean;
import com.example.chenyunpeng.youhuo.bena.HomeBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class AddCartDiaglog extends BaseDiaglog implements View.OnClickListener {
    protected View rootView;
    protected ImageView cartIv;
    protected TextView cartTv1;
    protected TextView cartTv2;
    protected TextView cartTv3Color;
    protected RadioButton rb1;
    protected RadioButton rb2;
    protected RadioButton rb3;
    protected RadioGroup colorGroup;
    protected TextView cartTv4Chima;
    protected RadioButton rb1Chima;
    protected RadioButton rb2Chima;
    protected RadioButton rb3Chima;
    protected RadioGroup chimaGroup;
    protected LinearLayout chimagroup;
    protected TextView cartTv5Shuliang;
    protected LinearLayout addorjian;
    private TextView shengyu;
    private Button queding;

    public AddCartDiaglog(Context context, BranBean branBean) {
        super(context);
        View inflate = View.inflate(a, R.layout.diaglog_addcart, null);
        setContentView(inflate);
        initView(inflate);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.BOTTOM);
        inflate.measure(0, View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, View.MeasureSpec.AT_MOST));
        getWindow().setLayout(width,inflate.getMeasuredHeight());
        getWindow().setWindowAnimations(R.style.addcart_diaglog);
        init(branBean);
    }

    private void init(BranBean branBean) {
        String imgpath = branBean.getImg().get(0).getImgpath();
        Picasso.with(getContext()).load(HttpModel.IMAGEHOST+imgpath).fit().into(cartIv);
        cartTv1.setText(branBean.getGoods().get(0).getTitle());
        cartTv2.setText(branBean.getGoods().get(0).getPrice());
    }


    private void initView(View rootView) {
        shengyu = (TextView) rootView.findViewById(R.id.shengyu);
        cartIv = (ImageView) rootView.findViewById(R.id.cart_iv);
        cartTv1 = (TextView) rootView.findViewById(R.id.cart_tv1);
        cartTv2 = (TextView) rootView.findViewById(R.id.cart_tv2);
        cartTv3Color = (TextView) rootView.findViewById(R.id.cart_tv3_color);
        rb1 = (RadioButton) rootView.findViewById(R.id.rb1);
        rb2 = (RadioButton) rootView.findViewById(R.id.rb2);
        rb3 = (RadioButton) rootView.findViewById(R.id.rb3);
        colorGroup = (RadioGroup) rootView.findViewById(R.id.color_group);
        cartTv4Chima = (TextView) rootView.findViewById(R.id.cart_tv4_chima);
        rb1Chima = (RadioButton) rootView.findViewById(R.id.rb1_chima);
        rb2Chima = (RadioButton) rootView.findViewById(R.id.rb2_chima);
        rb3Chima = (RadioButton) rootView.findViewById(R.id.rb3_chima);
        chimaGroup = (RadioGroup) rootView.findViewById(R.id.chima_group);
        chimagroup = (LinearLayout) rootView.findViewById(R.id.chimagroup);
        cartTv5Shuliang = (TextView) rootView.findViewById(R.id.cart_tv5_shuliang);
        addorjian = (LinearLayout) rootView.findViewById(R.id.addorjian);
        queding = (Button) rootView.findViewById(R.id.queding);
        queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
