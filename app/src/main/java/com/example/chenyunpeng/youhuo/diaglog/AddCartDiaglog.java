package com.example.chenyunpeng.youhuo.diaglog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;

import butterknife.Bind;

/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class AddCartDiaglog extends BaseDiaglog {
    public AddCartDiaglog(Context context) {
        super(context);
        View inflate = View.inflate(a, R.layout.diaglog_addcart, null);
        setContentView(inflate);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(width, Dp2Px.dp2px(400));
        getWindow().setWindowAnimations(R.style.addcart_diaglog);
    }
}
